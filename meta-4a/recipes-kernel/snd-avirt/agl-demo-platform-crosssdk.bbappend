TOOLCHAIN_TARGET_TASK += "${@bb.utils.contains("DISTRO_FEATURES", "agl-audio-4a-with-avirt", "libavirt-staticdev", "",d)}"
