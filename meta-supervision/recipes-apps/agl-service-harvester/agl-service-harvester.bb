SUMMARY     = "AGL Data Harvester Service/API"
DESCRIPTION = "V2C interface that collect data to TimeSeries database"
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-harvester/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-harvester;protocol=http;branch=sandbox/SebD/wip"
SRCREV = "AUTOREV"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake aglwgt pkgconfig

DEPENDS += ""

