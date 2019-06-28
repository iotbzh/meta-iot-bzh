SUMMARY     = "4A Mixer"
DESCRIPTION = "AGL HMI application for control of 4A mixer elements"
HOMEPAGE    = "http://git.ovh.iot/4a-legacy/"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "gitsm://git.ovh.iot/4a-legacy/mixer.git;protocol=http;branch=4a-legacy"
SRCREV  = "0f19df88ec387747300c9da05987f794a19461cc"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "qtquickcontrols2 \
            qtwebsockets \
            qtaglextras \
            libafb-helpers-qt \
"

PROVIDES += "virtual/mixer"
RPROVIDES_${PN} += "virtual/mixer"

inherit cmake_qt5 aglwgt

OECMAKE_CXX_FLAGS_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', '' , '-DQT_NO_DEBUG_OUTPUT', d)}"
