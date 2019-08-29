# Copyright (C) 2018-2019 
#		Stephane Desneux <stephane.desneux@iot.bzh>
#		Ronan Le Martret <ronan.lemartret@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "AGL hardware platform detection tools"
DESCRIPTION = "Scripts used to generate hardware information in /etc/platform-info"
HOMEPAGE    = "https://www.automotivelinux.org/"
SECTION     = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PV = "0.1"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "${PN}.service"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

SRC_URI = "\
    file://platform-detect \
    file://*.sh \
"

do_install_append() {
    BASEDIR=${libexecdir}/${PN}

    install -d ${D}${BASEDIR}/
    install -d ${D}${BASEDIR}/hw-detect.d
    install -m 0755 ${WORKDIR}/platform-detect ${D}${BASEDIR}/
    install -m 0755 ${WORKDIR}/??_*.sh ${D}${BASEDIR}/hw-detect.d

    mkdir -p ${D}${systemd_system_unitdir}/
    cat <<EOF >>${D}${systemd_system_unitdir}/${PN}.service
[Unit]
Description=${PN}
DefaultDependencies=no
Before=systemd-modules-load.service

[Service]
Type=oneshot
ExecStart=${BASEDIR}/platform-detect --fragments-dir ${BASEDIR}/hw-detect.d --output-file /etc/platform-info/hardware --json-file /etc/platform-info/hardware.json

[Install]
WantedBy=systemd-modules-load.service
EOF
}

RDEPENDS_${PN} = "bash"
FILES_${PN} += "${systemd_system_unitdir}"
FILES_${PN} += "${BASEDIR}/hw-detect.d"
