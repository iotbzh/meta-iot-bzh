SUMMARY     = "Open-Source Large Vocabulary Continuous Speech Recognition Engine"
HOMEPAGE    = "https://github.com/julius-speech/julius"
AUTHOR      = "Akinobu Lee"
SECTION     = "multimedia"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "\
    file://LICENSE;md5=ed79a2133cdcf1188c0794831100e051 \
"

ALLOW_EMPTY_${PN} = "1"

#SRC_URI = "gitsm://github.com/julius-speech/julius;protocol=https;branch=master"
SRC_URI = "gitsm://github.com/iotbzh/julius;protocol=https;branch=master"
#SRCREV = "73c0fa6d15735921333346d4a52e8491d89c84a1"
SRCREV = "${AUTOREV}"

PV = "4.5+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit meson pkgconfig

DEPENDS += "\
   libsndfile1 \
   zlib \
"

PACKAGECONFIG ??= "\
   alsa \
"

PACKAGECONFIG[alsa] = "-Dmictype=alsa,,alsa-lib"
