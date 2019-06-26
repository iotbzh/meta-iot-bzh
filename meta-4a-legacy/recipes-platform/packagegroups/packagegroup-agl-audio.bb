SUMMARY = "AGL Audio packages"
DESCRIPTION = "The set of packages required by the AGL Audio"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-audio \
    "

RDEPENDS_${PN} += "\
    4a-alsa-core \
    agl-service-audio-4a \
    VIRTUAL-RUNTIME_alsa-state \
    agl-service-unicens \
    bluez-alsa \
    4a-softmixer \
    4a-hal-generic \
    4a-hal-unicens \
    snd-avirt \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', '4a-tools', '' , d)} \
"
# FIXME: Disable for now due to compilation issues against PulseAudio 12.x
#    ${@bb.utils.contains('DISTRO_FEATURES', 'pulseaudio', 'pulseaudio-module-4a-client', '' , d)}
