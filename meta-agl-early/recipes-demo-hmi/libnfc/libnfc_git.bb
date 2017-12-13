SUMMARY     = "libnfc"
DESCRIPTION = ""
HOMEPAGE    = "https://www.github.com/iotbzh/nfc-binding"
SECTION     = "apps"
DEPENDS     = "libusb"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit cmake pkgconfig

SRC_URI = "git://github.com/nfc-tools/libnfc;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "2d4543673e9b76c02679ca8b89259659f1afd932"

PV = "1.7.1+git${SRCPV}"
S  = "${WORKDIR}/git"

