SUMMARY = "4A packages"
DESCRIPTION = "The set of packages required by 4A"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-4a \
    "

RDEPENDS_${PN} += "\
    4a-alsa-core \
    agl-service-audio-4a \
    agl-service-unicens \
    agl-service-unicens-controller \
    bluez-alsa \
    4a-softmixer \
    4a-hal-generic \
    4a-hal-unicens \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', '4a-tools', '' , d)} \
	${@bb.utils.contains("DISTRO_FEATURES", "agl-audio-4a-with-avirt", "snd-avirt", "",d)} \
"
