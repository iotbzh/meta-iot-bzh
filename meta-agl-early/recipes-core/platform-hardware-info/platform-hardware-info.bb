# Copyright (C) 2018 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "AGL hardware platform detection tools"
DESCRIPTION = "Scripts used to generate hardware information in /etc/platform-info"
HOMEPAGE    = "https://www.automotivelinux.org/"
SECTION     = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PV = "0.1"

SRC_URI = "\
	file://hw-detect \
	file://*.sh \
"

do_install_append() {
	BASEDIR=${libexecdir}/${PN}

	install -d ${D}$BASEDIR/
	install -d ${D}$BASEDIR/hw-detect.d
	install -m 0755 ${WORKDIR}/hw-detect ${D}$BASEDIR/
	install -m 0755 ${WORKDIR}/??_*.sh ${D}$BASEDIR/hw-detect.d

	# generate a firstboot fragment for detection
	install -d ${D}${sysconfdir}/agl-postinsts
	cat <<EOF >>${D}/${sysconfdir}/agl-postinsts/05_platform-hardware-info-detect.sh
$BASEDIR/hw-detect
EOF
	chmod a+x ${D}/${sysconfdir}/agl-postinsts/05_platform-hardware-info-detect.sh
}

RDEPENDS_${PN} = "bash"
