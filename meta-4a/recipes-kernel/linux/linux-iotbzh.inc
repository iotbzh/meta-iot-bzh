FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# snd-aloop support for 4a
SRC_URI_append = " file://4a-sound.cfg"
KERNEL_CONFIG_FRAGMENTS_append = " ${WORKDIR}/4a-sound.cfg"

# patch for snd-aloop to support more than 8 subdevices
SRC_URI_append = "\
	file://snd-aloop-128-subdevices.patch \
"
