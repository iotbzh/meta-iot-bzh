SUMMARY     = "agl-service-signal-composer"
DESCRIPTION = ""
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-signal-composer/"
SECTION     = "apps"
DEPENDS     = "af-binder"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit cmake pkgconfig aglwgt

SRC_URI = "gitsm://git.automotivelinux.org/apps/agl-service-signal-composer;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "bf0813cde040efd6ba74fc4947ed0531493084d0"

PV = "4.0-RC4+git${SRCPV}"
S  = "${WORKDIR}/git"

