SUMMARY     = "4A - Mixer"
DESCRIPTION = "4A Mixer (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/4a-softmixer/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://git.ovh.iot/4a/4a-mixer.git;protocol=http;branch=${4A_BRANCH}"
SRCREV = "${AUTOREV}"

DEPENDS += "lua liburcu libafb-helpers libappcontroller"

# optional dep and flags for avirt
DEPENDS += "${@bb.utils.contains("DISTRO_FEATURES", "agl-audio-4a-with-avirt", "libavirt", "",d)}"
EXTRA_OECMAKE += "${@bb.utils.contains("DISTRO_FEATURES", "agl-audio-4a-with-avirt", "-DHAVE_AVIRT=1", "",d)}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit afb-system-cmake

# FIXME: Remove once CMake+ninja issues are resolved
OECMAKE_GENERATOR = "Unix Makefiles"

#FIXME :
#FILES_${PN}-dev += "${INSTALL_PREFIX}/4a-mixer/htdocs"
#FILES_${PN} += "${INSTALL_PREFIX}/afb-aaaa"
#FILES_${PN} += "${INSTALL_PREFIX}/lib"