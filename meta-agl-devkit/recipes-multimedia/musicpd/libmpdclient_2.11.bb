SUMMARY = "libmpdclient - Music Player Daemon client library"
HOMEPAGE = "https://www.musicpd.org/"
SECTION = "libs/multimedia"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=06b9dfd2f197dc514d8ef06549684b77"

SRC_URI = "https://www.musicpd.org/download/libmpdclient/2/libmpdclient-${PV}.tar.xz"
SRC_URI[md5sum] = "2b2795929bba9e59f31118c4eedfe309"
SRC_URI[sha256sum] = "15fe693893c0d7ea3f4c35c4016fbd0332836164178b20983eec9b470846baf6"

inherit autotools pkgconfig vala

EXTRA_OECONF = "--disable-documentation"
