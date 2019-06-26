# snd-aloop support for 4a
SRC_URI_append = " file://4a-sound.cfg"
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/4a-sound.cfg"

