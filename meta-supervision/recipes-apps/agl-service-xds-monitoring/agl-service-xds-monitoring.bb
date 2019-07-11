SUMMARY     = "XDS Monitoring Service"
DESCRIPTION = "Collects intrinsic bindings metrics from supervisor daemon as well as system resources consumption and send them to harvester binding for storage in a TimeDB."
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-xds-monitoring/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-xds-monitoring;protocol=http;branch=sandbox/SebD/wip"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake aglwgt pkgconfig

DEPENDS += "json-c systemd libafb-helpers libappcontroller"

