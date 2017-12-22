SUMMARY = "Chromium web browser"
DESCRIPTION = "Google Chromium Web Browser application widget wrapper for AGL App Framework"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI = " \
    file://config.xml \
    file://runxdg.toml \
    file://runxdg \
    "

do_compile() {
	cd ${B}
	mkdir -p wgtroot/bin package
	cp ${WORKDIR}/config.xml ${WORKDIR}/runxdg.toml wgtroot
	cp ${WORKDIR}/runxdg wgtroot/bin
	wgtpkg-pack -f -o package/agl-webbrowser.wgt wgtroot
}

inherit aglwgt
