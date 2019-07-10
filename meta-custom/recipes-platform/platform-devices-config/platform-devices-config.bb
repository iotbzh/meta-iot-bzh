# Copyright (C) 2018 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "AGL platform devices configuration"
DESCRIPTION = "Scripts to configure platform devices after detection in platform-devices-info"
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
    file://platform-devices-config \
"

do_install_append() {
    BASEDIR=${libexecdir}/${PN}
    install -d ${D}${BASEDIR}/

    install -d ${D}${BASEDIR}/BLUETOOTH_DEVICES
    install -d ${D}${BASEDIR}/ETHERNET_DEVICES
    install -d ${D}${BASEDIR}/CAN_DEVICES
    install -d ${D}${BASEDIR}/WIFI_DEVICES
    install -d ${D}${BASEDIR}/COMMON

    install -m 0755 ${WORKDIR}/platform-devices-config ${D}${BASEDIR}

    mkdir -p ${D}${systemd_system_unitdir}/
    cat <<EOF >>${D}${systemd_system_unitdir}/${PN}.service
[Unit]
Description=${PN}
DefaultDependencies=no
After=platform-devices-info.service
Requires=platform-devices-info.service

[Service]
Type=oneshot
ExecStart=${BASEDIR}/platform-devices-config

[Install]
WantedBy=multi-user.target
EOF
}

RDEPENDS_${PN} = "bash platform-devices-info"
FILES_${PN} += "${systemd_system_unitdir}"
FILES_${PN} += "${BASEDIR}"
