SUMMARY     = "Voice Agent binding using Julius library"
HOMEPAGE    = "http://git.ovh.iot/speech/julius-voiceagent"
AUTHOR      = "Thierry Bultel <thierry.bultel@iot.bzh>"
SECTION     = "multimedia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://github.com/iotbzh/julius;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit afb-system-cmake

DEPENDS += "\
   julius
"

