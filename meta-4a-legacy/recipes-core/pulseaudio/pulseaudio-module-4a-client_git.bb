SUMMARY     = "pulseaudio 4A client module"
DESCRIPTION = "pulseaudio module to work with 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/src/pulseaudio-module-4a"
SECTION     = "plugins"

LICENSE = "LGPL-2.1+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/src/pulseaudio-module-4a.git;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "9cd72ed153f6615ed826b048e1c344bf4640bd2b"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake pkgconfig

DEPENDS += "af-binder systemd json-c pulseaudio"
RDEPENDS_${PN} = "pulseaudio-module-alsa-sink"

FILES_${PN} += "${libdir}/pulse-*/modules/module-4a-client.so"
