SUMMARY = "AVIRT user-space library"
DESCRIPTION = "ALSA Virtual Dynamic Sound Driver (AVIRT) user-space library for dynamic sound stream creation."
HOMEPAGE    = "https://git.automotivelinux.org/src/libavirt/"
SECTION     = "libs/multimedia"

LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/libavirt;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "379ea3c132f53eb6f5cf26d1c8a5ca335eb32a2e"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

DEPENDS = "alsa-lib"

inherit cmake

ALLOW_EMPTY_${PN} = "1"
