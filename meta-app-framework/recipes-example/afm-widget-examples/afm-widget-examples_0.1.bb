inherit allarch

SUMMARY = "Examples of widgets"
DESCRIPTION = "Example of widgets for AGL framework afm"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/iotbzh/afm-widget-examples;protocol=https;branch=master"
SRCREV = "58ca868490f9c814c5bb6a9a1b27532d337fabd7"

S = "${WORKDIR}/git"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    install -d ${D}${datadir}/widget-examples
    install -m 644 ${S}/*.wgt ${D}${datadir}/widget-examples
}

FILES_${PN} += "${datadir}/widget-examples/*.wgt"

