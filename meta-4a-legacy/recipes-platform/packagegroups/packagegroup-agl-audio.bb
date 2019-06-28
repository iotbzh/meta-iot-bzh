SUMMARY = "AGL Audio packages"
DESCRIPTION = "The set of packages required by the AGL Audio"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-audio \
    "

RDEPENDS_${PN} += "\
    4a-alsa-core \
    VIRTUAL-RUNTIME_alsa-state \
    4a-hal-generic \
    4a-hal-unicens \
    4a-softmixer \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', '4a-tools', '' , d)} \
    agl-service-audio-4a \
    agl-service-unicens \
    bluez-alsa \
    snd-avirt \
"
