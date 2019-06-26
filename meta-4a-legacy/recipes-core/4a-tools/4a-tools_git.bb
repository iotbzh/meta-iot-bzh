SUMMARY     = "4A - Tools"
DESCRIPTION = "Tools, utilities, scripts and data related to 4A"
HOMEPAGE    = "https://github.com/iotbzh/4a-tools/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://github.com/iotbzh/4a-tools;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "flounder_5.99.2"
#SRCREV = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"


do_install() {
    oe_runmake install DESTDIR=${D}${prefix}
}

RDEPENDS_${PN} += "bash python3-websockets"
FILES_${PN} += "${datadir}/4a/media/*"
