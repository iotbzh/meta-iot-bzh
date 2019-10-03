SUMMARY     = "4A - Generic HAL device configuration"
DESCRIPTION = "Generic HAL device configuration in 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-hal-configs/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://git.ovh.iot/4a/4a-hal-configs.git;protocol=http;branch=${4A_BRANCH}"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

# The package is machine-specific due to variable config content
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit afb-system
# This defines INSTALL_PREFIX = "/usr/libexec/agl/"

# no configure needed
do_configure[noexec] = "1"

# no compile needed
do_compile[noexec] = "1"

# when no specific HAL is defined, use a generic usb one
4A_HAL_LIST ??= "2ch-generic-usb"

# for specific machines, activate only known HALs
4A_HAL_LIST_m3ulcb          ?= "rcar-m3 rcar-m3kf"
4A_HAL_LIST_h3ulcb          ?= "rcar-m3 rcar-m3kf"
4A_HAL_LIST_intel-corei7-64 ?= "intel-minnow intel-upsquared-hdmi"
4A_HAL_LIST_qemux86-64      ?= "intel-qemu"

# Due to bug SPEC-1610, default hal for RPI3 is not active yet
# 4A_HAL_LIST_raspberrypi3    ?= "raspberry-pi-3"

do_install () {

    # get pkgdir for 4a-hal
    PKGDIR=${D}/${INSTALL_PREFIX}/4a-hal

    install -d -m 0755 ${PKGDIR}
    install -d -m 0755 ${PKGDIR}/etc
    install -d -m 0755 ${PKGDIR}/etc.available

    cp -ar ${S}/*/*.json ${PKGDIR}/etc.available/
    chown -R root:root ${PKGDIR}/etc.available

    for x in ${4A_HAL_LIST}; do
        hal=hal-4a-$x.json
        cp -v $PKGDIR/etc.available/${hal} $PKGDIR/etc/
    done
}


RPROVIDES_${PN} += "virtual/4a-default-hal"
