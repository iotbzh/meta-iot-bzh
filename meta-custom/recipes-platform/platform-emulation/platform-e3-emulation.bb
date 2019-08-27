# Copyright (C) 2018 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "AGL hardware platform emulation tools"
DESCRIPTION = "Scripts used to downgrade board performance in order to obtain specific board performance (from a bigger one)"
HOMEPAGE    = "https://www.automotivelinux.org/"
SECTION     = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PV = "0.1.1"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "${PN}.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

SRC_URI = "\
    file://e3-emulation \
"

do_install_append() {
    BASEDIR=${libexecdir}/${PN}

    install -d ${D}${BASEDIR}/
    install -m 0755 ${WORKDIR}/e3-emulation ${D}${BASEDIR}/

    mkdir -p ${D}${systemd_system_unitdir}/
    cat <<EOF >>${D}${systemd_system_unitdir}/${PN}.service
[Unit]
Description=platform-e3-emulation
DefaultDependencies=no
Before=systemd-modules-load.service

[Service]
Type=oneshot
ExecStart=/usr/libexec/platform-e3-emulation/e3-emulation start
ExecStop=/usr/libexec/platform-e3-emulation/e3-emulation stop
RemainAfterExit=yes

[Install]
WantedBy=systemd-modules-load.service
EOF
}

RDEPENDS_${PN} = "bash"
FILES_${PN} += "${systemd_system_unitdir}"
