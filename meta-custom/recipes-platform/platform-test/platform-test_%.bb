# Copyright (C) 2018-2019 
#		Stephane Desneux <stephane.desneux@iot.bzh>
#		Ronan Le Martret <ronan.lemartret@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY = "Basic hardware tests for different platforms"
DESCRIPTION = "Test hardware on different platforms."
HOMEPAGE = "https://github.com/iotbzh/meta-iot-bzh/tree/master/meta-agl-early/recipes-core/platform-test"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "\
   file://platform-test_* \
   file://tests/ \
   file://platform-test \
   file://run-ptest \
"

SRC_URI[md5sum]="5717b7fcaf8b6654238853768cbfc267"

RDEPENDS_${PN}  += "bash platform-runtime-tools"
RDEPENDS_${PN}-ptest += "bash platform-runtime-tools"

PV = "${AGLVERSION}"

inherit ptest

# create a dummy file to have platform-test package created
do_install() {
	install -d ${D}${bindir}/
	install -D -m 755 ${WORKDIR}/platform-test ${D}${bindir}

	install -d ${D}${datadir}/${PN}/tests
	install -D -m 755 ${WORKDIR}/platform-test_* ${D}${datadir}/${PN}
	install -D -m 755 ${WORKDIR}/tests/* ${D}${datadir}/${PN}/tests
}

