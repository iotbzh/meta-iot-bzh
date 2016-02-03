inherit allarch useradd

SUMMARY = "Examples of widgets"
DESCRIPTION = "Example of widgets for AGL framework afm"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/iotbzh/afm-widget-examples;protocol=https;branch=master"
SRCREV = "19e2d1de41bc8b25b21f1cbcf4bd31d9a18d67b6"

S = "${WORKDIR}/git"

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-g users -d /home/widget-examples widget-examples"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    mkdir -p ${D}/home/widget-examples
    cp ${S}/*.wgt ${D}/home/widget-examples
}

FILES_${PN} += "/home/widget-examples/*.wgt"

