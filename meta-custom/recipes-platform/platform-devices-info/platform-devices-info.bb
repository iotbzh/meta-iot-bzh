# Copyright (C) 2019 Ronan Le Martret <ronan.lemartret@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "AGL devices platform detection tools"
DESCRIPTION = "Scripts used to generate devices information in /etc/platform-info"
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
    file://*.sh \
"

do_install_append() {
    BASEDIR=${libexecdir}/platform-hardware-info

    install -d ${D}${BASEDIR}/dev-detect.d
    install -m 0755 ${WORKDIR}/??_*.sh ${D}${BASEDIR}/dev-detect.d

    mkdir -p ${D}${systemd_system_unitdir}/
    cat <<EOF >>${D}${systemd_system_unitdir}/${PN}.service
[Unit]
Description=${PN}
After=systemd-modules-load.service
After=platform-hardware-config.service
Requires=platform-hardware-config.service

[Service]
Type=oneshot
ExecStart=${BASEDIR}/platform-detect --fragments-dir ${BASEDIR}/dev-detect.d --output-file /etc/platform-info/devices --
RemainAfterExit=yes

[Install]
WantedBy=multi-user.target
EOF
}

RDEPENDS_${PN} = "bash platform-hardware-info platform-hardware-config"
RDEPENDS_${PN} = "bash"
FILES_${PN} += "${systemd_system_unitdir}"
FILES_${PN} += "${BASEDIR}/dev-detect.d"
