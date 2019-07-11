SUMMARY     = "AGL Data Harvester Service/API"
DESCRIPTION = "V2C interface that collect data to TimeSeries database"
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-harvester/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-harvester;protocol=http;branch=sandbox/SebD/wip"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake aglwgt pkgconfig

DEPENDS += "json-c systemd libafb-helpers libappcontroller curl"

