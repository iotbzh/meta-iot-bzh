SUMMARY     = "agl-service-ctlapp-sample"
DESCRIPTION = "AGL ctlapp sample service to handle applciation foreground"
HOMEPAGE    = "https://github.com/iotbzh/agl-services-ctlapp-sample.git"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit cmake pkgconfig aglwgt ptest

DEPENDS += "lua lua-native"
RDEPENDS_${PN} += "lua"

SRC_URI = "gitsm://github.com/iotbzh/agl-services-ctlapp-sample.git;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AUTOREV}"

PV = "${AGLVERSION}"
S  = "${WORKDIR}/git"

