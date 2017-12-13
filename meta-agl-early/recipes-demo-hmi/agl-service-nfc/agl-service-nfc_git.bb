SUMMARY     = "agl-service-nfc"
DESCRIPTION = ""
HOMEPAGE    = "https://www.github.com/iotbzh/nfc-binding"
SECTION     = "apps"
DEPENDS     = "af-binder json-c libnfc"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit cmake pkgconfig aglwgt

SRC_URI = "gitsm://github.com/iotbzh/nfc-binding;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "f9ffdbe78f4ab0f414f714861c5431fadc891510"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

