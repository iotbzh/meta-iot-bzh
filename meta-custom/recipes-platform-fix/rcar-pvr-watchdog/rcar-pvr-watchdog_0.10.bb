# Copyright (C) 2019 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY = "Watchdog to reboot rcar boards due to evaluation gfx driver"
DESCRIPTION = "This package adds a script 'pvr-watchdog' triggered regularly. When conditions are met, the script will reboot the board."
HOMEPAGE = "https://www.automotivelinux.org/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PV = "0.10"

SRC_URI = "\
    file://pvr-watchdog \
    file://pvr-watchdog.service \
    file://pvr-watchdog.timer \
"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit systemd

do_install () {
    install -d ${D}${bindir}/
    install -m 0755 ${WORKDIR}/pvr-watchdog ${D}${bindir}/
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/pvr-watchdog.service ${D}${systemd_system_unitdir}/
        install -m 0644 ${WORKDIR}/pvr-watchdog.timer ${D}${systemd_system_unitdir}/
    fi
}

SYSTEMD_SERVICE_${PN} = "pvr-watchdog.service pvr-watchdog.timer"

RDEPENDS_${PN} += "bash"

