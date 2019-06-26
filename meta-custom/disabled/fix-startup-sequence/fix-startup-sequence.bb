# Copyright (C) 2018 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY = "AGL hotfixes for startup sequence"
DESCRIPTION = "Workaround for issues on homescreen/windowmanager and bluetooth startup"
HOMEPAGE = "https://www.automotivelinux.org/"
SECTION = "support"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PV = "0.1"

SRC_URI = "\
	file://bluealsa_wait_bt.sh \
"

do_install_append() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/bluealsa_wait_bt.sh ${D}${bindir}

	# generate a firstboot fragment for detection
	install -d ${D}${sysconfdir}/agl-postinsts

	cat <<'EOF' >>${D}/${sysconfdir}/agl-postinsts/B0_${PN}.sh
srcdir=/var/local/lib/systemd/system/

logger -t $(basename $BASH_SOURCE) "WORKAROUND!!! HOT PATCH ON HOMESCREEN AND WINDOWMANAGER"
for svc in \
	service-windowmanager-service \
	service-homescreen-service \
	; do
    for x in $srcdir/afm-$svc--*@.service; do
		# wait for weston to be ready
		sed -i '/\[Unit\]/ a After=weston-ready.service' $x
		sed -i '/\[Unit\]/ a Requires=weston-ready.service' $x
    done
done

logger -t $(basename $BASH_SOURCE) "WORKAROUND!!! HOT PATCH ON HOMESCREEN AND LAUNCHER"
for svc in \
	appli-homescreen \
	appli-launcher \
	; do
    for x in $srcdir/afm-$svc--*@.service; do
		sed -i '/\[Service\]/ a Restart=always' $x
	done
done

logger -t $(basename $BASH_SOURCE) "WORKAROUND!!! HOT PATCH ON BLUEALSA"
svcfile=/lib/systemd/system/bluez-alsa.service
if [[ -f $svcfile ]]; then
	sed -i -e 's|/usr/bin/bluealsa\>|/usr/bin/bluealsa_wait_bt.sh &|' $svcfile
fi


EOF
	chmod a+x ${D}/${sysconfdir}/agl-postinsts/B0_${PN}.sh
}

RDEPENDS_${PN} = "bash"
