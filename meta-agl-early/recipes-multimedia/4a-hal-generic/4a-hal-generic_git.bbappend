FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://hal-4a-greenbox.json"

do_install_append () {
	cp ${WORKDIR}/hal-4a-greenbox.json $PKGDIR/etc.available
}
