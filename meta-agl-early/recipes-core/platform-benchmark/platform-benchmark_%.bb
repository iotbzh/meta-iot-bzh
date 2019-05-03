SUMMARY     = "Script to run benchmark tools"
DESCRIPTION = "Run a subset of defined stress and benchmark test in order to obtain an indicator of the system performance."
HOMEPAGE = "https://github.com/iotbzh/meta-iot-bzh/tree/master/meta-agl-early/recipes-core/platform-benchmark"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI =  " \
    file://agl-benchmark \
"

PV = "0.1"

RDEPENDS_${PN}  += "bash"

SRC_URI[md5sum]="0ed923c8b3d17cdf91c8f20e9a634706"

do_install () {
    install -d ${D}/${sbindir}
    install -m 0755 ${WORKDIR}/agl-benchmark ${D}/${sbindir}
}
