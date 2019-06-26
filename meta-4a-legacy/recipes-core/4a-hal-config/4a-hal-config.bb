SUMMARY     = "4A - HAL Configuration"
DESCRIPTION = "Configuration files for HALs used in 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-hal-reference/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://asound.conf.template \
           "

PV = "0.1"

RPROVIDES_${PN} += "VIRTUAL-RUNTIME_alsa-state"

inherit afb-system

do_install () {
    install -d ${D}/${sysconfdir}
    install -m 0755 ${WORKDIR}/asound.conf.template ${D}/${sysconfdir}/asound.conf
    sed -i "s|@INSTALL_PREFIX@|${INSTALL_PREFIX}|g" ${D}/${sysconfdir}/asound.conf
}

FILES_${PN} += "${sysconfdir}/asound.conf"
