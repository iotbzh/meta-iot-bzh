FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN} = "bash"

4A_RUNTIME_HAL_FALLBACK = "2ch-generic-usb"
4A_RUNTIME_HAL_AUTODETECT = "yes"

SRC_URI += "\
	file://4a-hal-setup \
	file://0001-hal-4a-rcar-m3-use-same-API-name-as-hal-greenbox.patch \
"

do_install_append () {
	# install setupscript that will enable the proper HAL(s)
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/4a-hal-setup ${D}${bindir}

	# generate default config file with platform hals
	cat <<EOF >$PKGDIR/default_hals.conf
HAL_DEFAULT="${4A_HAL_LIST}"
HAL_FALLBACK="${4A_RUNTIME_HAL_FALLBACK}"
HAL_AUTODETECT="${4A_RUNTIME_HAL_AUTODETECT}"
EOF

	# generate a fragment to patch 4a service file on boot
    install -d ${D}${sysconfdir}/agl-postinsts
	cat <<'EOF' >${D}/${sysconfdir}/agl-postinsts/A0_4a-hal-device-config_svcpatch.sh
N=600
svcfile="/usr/local/lib/systemd/*/afm-service-agl-service-audio-4a*.service"
set -x
while ! ls $svcfile > /dev/null; do
	if [ $N = 0 ]; then echo "-- TMP 4A service not fixed for HAL detection"; exit 0; fi
	echo .
	sleep 0.2
	N=$(expr $N - 1)
done
EOF

	cat <<EOF >>${D}/${sysconfdir}/agl-postinsts/A0_4a-hal-device-config_svcpatch.sh
sed -i '/\[Service\]/ a ExecStartPre=${bindir}/4a-hal-setup ${INSTALL_PREFIX}/4a-hal/default_hals.conf' \$svcfile;
EOF
	chmod a+x ${D}/${sysconfdir}/agl-postinsts/A0_4a-hal-device-config_svcpatch.sh

	# remove all config files from etc as runtime setup will be done
	rm -f $PKGDIR/etc/*.json
}

