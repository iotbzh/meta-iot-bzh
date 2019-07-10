# Copyright (C) 2019 Ronan Le Martret <ronan.lemartret@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "AGL platform hardware configuration"
DESCRIPTION = "Scripts used to configure devices after harware detection in platform-hardware-info"
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
    file://btwilink-disable.sh \
    file://platform-hardware-config \
"

do_install_append() {
    BASEDIR=${libexecdir}/${PN}
    install -d ${D}${BASEDIR}/

    install -d ${D}${BASEDIR}/SCRIPT
    install -d ${D}${BASEDIR}/CPU_ARCH
    install -d ${D}${BASEDIR}/SOC_VENDOR
    install -d ${D}${BASEDIR}/BOARD_MODEL
    install -d ${D}${BASEDIR}/COMMON

    install -m 0755 ${WORKDIR}/platform-hardware-config ${D}${BASEDIR}
    install -m 0755 ${WORKDIR}/btwilink-disable.sh ${D}${BASEDIR}/SCRIPT

    install -d  ${D}${BASEDIR}/SOC_VENDOR/Renesas
    ln -s ${BASEDIR}/SCRIPT/btwilink-disable.sh ${D}${BASEDIR}/SOC_VENDOR/Renesas/btwilink-disable.sh

    mkdir -p ${D}${systemd_system_unitdir}/
    cat <<EOF >>${D}${systemd_system_unitdir}/${PN}.service
[Unit]
Description=${PN}
DefaultDependencies=no
Before=systemd-modules-load.service
After=platform-hardware-info.service
Requires=platform-hardware-info.service

[Service]
Type=oneshot
ExecStart=${BASEDIR}/platform-hardware-config

[Install]
WantedBy=systemd-modules-load.service
EOF
}

RDEPENDS_${PN} = "bash platform-hardware-info"
FILES_${PN} += "${systemd_system_unitdir}"
FILES_${PN} += "${BASEDIR}"
