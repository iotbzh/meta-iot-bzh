SUMMARY = "A example of usage of the Generic Controller binding"
DESCRIPTION = "This is a binding example with a controller configuration and plugins to be used by the Generic Controlle"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://github.com/iotbzh/agl-services-ctlapp-sample.git;protocol=https;branch=controller-sample"
SRCREV = "${AUTOREV}"

PV = "${AGLVERSION}"
S  = "${WORKDIR}/git"

DEPENDS_append = " libappcontroller libafb-helpers"

inherit cmake aglwgt 

