FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN} = "bash"

SRC_URI += " \
	file://4a-hal-setup \
	file://hal-4a-greenbox.json \
"

do_install_append () {
	# move all HALS from etc to etc.available
	mkdir -p $PKGDIR/etc.available/
	mv $PKGDIR/etc/* $PKGDIR/etc.available/ 2>/dev/null || true
	
	# add greenbox hal
	cp ${WORKDIR}/hal-4a-greenbox.json $PKGDIR/etc.available/

	# install setupscript that will enable the proper HAL(s)
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/4a-hal-setup ${D}${bindir}

	# then generate a scriptlet that will invoke 4a-hal-setup at startup
	install -d ${D}${sysconfdir}/agl-postinsts
	cat <<EOF >${D}/${sysconfdir}/agl-postinsts/05_4a-hal-generic.sh
${bindir}/4a-hal-setup ${4A_RUNTIME_HAL} 2>&1 | logger -s -t agl-postinsts/10_4a-hal-generic.sh
EOF
	chmod 755 ${D}/${sysconfdir}/agl-postinsts/05_4a-hal-generic.sh
}
