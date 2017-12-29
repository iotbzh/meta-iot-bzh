DESCRIPTION = "AGL Spotify app"
SECTION = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"
SRC_URI = " \
    file://spotifyplayer.tar.gz \
    file://config.xml \
    file://runxdg \
    file://runxdg.toml \
    "

inherit aglwgt

PV = "5.0"
S = "${WORKDIR}/git"

DEPENDS += " json-c systemd curl "
RDEPENDS_${PN} += "libasound"

do_compile() {
	cd ${B}
	mkdir -p wgtroot/htdocs wgtroot/bin package
	cp -r ${WORKDIR}/config.xml ${WORKDIR}/runxdg.toml wgtroot
	cp -r ${WORKDIR}/runxdg wgtroot/bin
	cp -r ${WORKDIR}/src/* wgtroot/htdocs
	wgtpkg-pack -f -o package/agl-spotify.wgt wgtroot
}
