FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-Fix-Spotify-app-not-displayed-in-layer.patch;patch=1 \
"
