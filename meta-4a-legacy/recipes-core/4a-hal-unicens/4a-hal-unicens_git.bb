SUMMARY     = "4A - Unicens (MOST) Binding"
DESCRIPTION = "Unicens binding (MOST audio network support) for 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-hal-unicens/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e385f6075dcdf3ad6ff0056fca58a129"

DEPENDS += "lua libafb-helpers libappcontroller"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/src/4a-hal-unicens;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "9228c9da5b85f0fdd6b01f0309e57b846618be63"


PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit afb-system-cmake

# FIXME: Remove once CMake+ninja issues are resolved
OECMAKE_GENERATOR = "Unix Makefiles"

do_install_append () {
    # installation folder for this plugin is '4a-hal'
    # get pkgdir - note that '4a-hal' comes from project ${project_git_repo}/conf.d/cmake/config.cmake
    PKGDIR=${D}/${INSTALL_PREFIX}/4a-hal

    # transition: provided by separate package
    rm -rf $PKGDIR/etc
}


