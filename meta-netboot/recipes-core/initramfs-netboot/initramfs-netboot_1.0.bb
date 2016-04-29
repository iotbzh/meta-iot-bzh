SUMMARY = "Extremely basic live image init script"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SRC_URI = "file://init.sh"

S = "${WORKDIR}"

do_install() {
	install -dm 0755 ${D}/etc
	touch ${D}/etc/initrd-release
	install -dm 0755 ${D}/dev
        install -m 0755 ${WORKDIR}/init.sh ${D}/init
}

inherit allarch

FILES_${PN} += " /dev /etc/initrd-release /init "

