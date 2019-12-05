SUMMARY     = "4A - Low Level Alsa Binding"
DESCRIPTION = "Low Level Alsa Binding for 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-alsa-core/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "gitsm://git.ovh.iot/4a/4a-alsacore.git;protocol=http;branch=${4A_BRANCH}"
SRCREV = "${AUTOREV}"

DEPENDS += "libafb-helpers liburcu"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit afb-system-cmake

# FIXME: Remove once CMake+ninja issues are resolved
OECMAKE_GENERATOR = "Unix Makefiles"

FILES_${PN}-dev += "${INSTALL_PREFIX}/4a-alsa-core/htdocs"

FILES_${PN} += "${INSTALL_PREFIX}/4a-alsa-core"
FILES_${PN} += "${INSTALL_PREFIX}/lib"
