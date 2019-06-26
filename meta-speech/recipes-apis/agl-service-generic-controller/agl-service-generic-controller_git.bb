SUMMARY = "Generic Controller binding for the Application Framework"
DESCRIPTION = "A generic controller binding to be used from external bindings with configuration and plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "gitsm://github.com/iotbzh/agl-service-generic-controller.git;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AUTOREV}"

PV = "${AGLVERSION}"
S  = "${WORKDIR}/git"

DEPENDS_append = " libappcontroller lua"
RDEPENDS_${PN}_append = " lua"

inherit cmake aglwgt

