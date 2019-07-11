SUMMARY     = "System metrics collector Service/API"
DESCRIPTION = "Low collector based on the collectd library"
HOMEPAGE    = "https://iot.bzh/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "gitsm://git.ovh.iot/marco/agl-service-low-collector.git;protocol=http;branch=master"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake aglwgt pkgconfig

DEPENDS += "json-c systemd libafb-helpers"

