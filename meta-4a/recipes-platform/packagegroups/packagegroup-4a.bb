SUMMARY = "4A packages"
DESCRIPTION = "The set of packages required by 4A"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-4a \
    "

RDEPENDS_${PN} += "\
    4a-alsacore \
    4a-highlevel \
    agl-service-unicens \
    agl-service-unicens-controller \
    bluez-alsa \
    4a-mixer \
    4a-hal \
    4a-hal-unicens \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', '4a-tools', '' , d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "agl-audio-4a-with-avirt", "snd-avirt", "",d)} \
"
