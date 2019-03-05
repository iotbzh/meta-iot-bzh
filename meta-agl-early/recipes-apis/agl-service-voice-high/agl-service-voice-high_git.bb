SUMMARY     = "agl-service-voice-high"
DESCRIPTION = "AGL High Level Voice service"
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-voice-high"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit cmake pkgconfig aglwgt

DEPENDS += "lua lua-native"
RDEPENDS_${PN} += "lua"

SRC_URI = "gitsm://github.com/iotbzh/agl-service-voice-high.git;protocol=https;branch=${AGL_BRANCH} \
file://0001-VoiceAgentEventsHandler-handle-mic-states.patch"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

