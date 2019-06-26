SUMMARY     = "4A - Generic HAL"
DESCRIPTION = "Generic HAL in 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-hal-generic/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/src/4a-hal-generic;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "0c4921b83a13bcb96bf03fb0e7a0733c3e202474"

DEPENDS += "lua bluez-alsa liburcu libafb-helpers libappcontroller"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit afb-system-cmake

# FIXME: Remove once CMake+ninja issues are resolved
OECMAKE_GENERATOR = "Unix Makefiles"

do_install_append () {
    # get pkgdir - note that '4a-hal' comes from project ${project_git_repo}/conf.d/cmake/config.cmake
    PKGDIR=${D}/${INSTALL_PREFIX}/4a-hal

    # transition: provided by separate package
    rm -rf $PKGDIR/etc
}

RDEPENDS_${PN} += "virtual/4a-default-hal bluez-alsa"
