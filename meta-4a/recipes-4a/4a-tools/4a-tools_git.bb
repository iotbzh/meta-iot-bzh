SUMMARY     = "4A - Tools"
DESCRIPTION = "Tools, utilities, scripts and data related to 4A"
HOMEPAGE    = "https://github.com/iotbzh/4a-tools/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://git.ovh.iot/4a/4a-tools.git;protocol=http;branch=${4A_BRANCH}"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"


do_install() {
    oe_runmake install DESTDIR=${D}${prefix}
}

RDEPENDS_${PN} += "bash python3-websockets"
FILES_${PN} += "${datadir}/4a/media/*"