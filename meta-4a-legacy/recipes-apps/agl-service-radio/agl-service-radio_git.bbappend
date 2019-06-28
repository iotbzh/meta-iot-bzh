SUMMARY     = "Radio Service Binding"
DESCRIPTION = "AGL Radio Service Binding (4A version)"
HOMEPAGE    = "http://git.ovh.iot/4a-legacy/"

SRC_URI = "gitsm://git.ovh.iot/4a-legacy/agl-service-radio.git;protocol=http;branch=4a-legacy"
SRCREV  = "a27f3b216008473d3150a3390aadedeaca868d63"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'agl-audio-4a-framework', '' , bb.utils.contains('DISTRO_FEATURES','pulseaudio','pulseaudio','',d), d)}"

EXTRA_OECMAKE += "${@bb.utils.contains('DISTRO_FEATURES', 'agl-audio-4a-framework', '-DHAVE_4A_FRAMEWORK=1' , '', d)}"

