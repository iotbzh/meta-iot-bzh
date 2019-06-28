# Remove PulseAudio plugin configuration files

do_install_append() {
	rm -f ${D}${datadir}/alsa/alsa.conf.d/*pulseaudio*
}

FILES_${PN}-pulseaudio-conf = "${datadir}/alsa/alsa.conf.d"
