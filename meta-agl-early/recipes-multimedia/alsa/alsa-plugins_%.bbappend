do_install_append() {
	# We use the example as is, so just drop the .example suffix.
	if [ "${@bb.utils.contains('PACKAGECONFIG', 'pulseaudio', 'yes', 'no', d)}" = "yes" ]; then
		mkdir -p ${D}${datadir}/alsa/alsa.conf.d.disable
		mv ${D}${datadir}/alsa/alsa.conf.d/* ${D}${datadir}/alsa/alsa.conf.d.disable/
		rm -fr ${D}${datadir}/alsa/alsa.conf.d
	fi
}

FILES_${PN}-pulseaudio-conf += "\
	${datadir}/alsa/alsa.conf.d.disable/50-pulseaudio.conf \
	${datadir}/alsa/alsa.conf.d.disable/99-pulseaudio-default.conf \
"

