SUMMARY     = "Voice Agent binding using Julius library"
HOMEPAGE    = "http://git.ovh.iot/speech/julius-voiceagent"
AUTHOR      = "Thierry Bultel <thierry.bultel@iot.bzh>"
SECTION     = "multimedia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://git.ovh.iot/speech/julius-voiceagent.git;protocol=http;branch=master"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit afb-system-cmake aglwgt

OECMAKE_GENERATOR = "Unix Makefiles"

DEPENDS += "\
   libappcontroller \
   libafb-helpers \
   julius \
"

RDEPENDS_${PN} += "python"

#### HACK, the rate conversion and mono donwmix are not implemented by 4a yet,
#### This is done through asound.conf #####

RPROVIDES_${PN} += "VIRTUAL-RUNTIME_alsa-state"

do_install_append() {
	install -d ${D}/etc/
	install ${S}/src/plugins/julius/conf/asound.conf ${D}/etc/

	install -d ${D}${bindir}
	install -m 0755 ${S}/src/plugins/julius/tools/prevoca2voca.py ${D}${bindir}/
}

