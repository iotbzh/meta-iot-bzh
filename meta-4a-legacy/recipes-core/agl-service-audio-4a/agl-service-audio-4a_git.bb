SUMMARY     = "4A - High Level Audio API Service"
DESCRIPTION = "High Level Audio API service used in 4A (AGL Advanced Audio Agent)"
HOMEPAGE    = "https://git.automotivelinux.org/apps/agl-service-audio-4a/"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/agl-service-audio-4a;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "${AGL_APP_REVISION}"

PV = "0.1+git${SRCPV}"
S  = "${WORKDIR}/git"

inherit cmake aglwgt pkgconfig

DEPENDS += "alsa-lib json-c systemd af-binder glib-2.0 lua libappcontroller"

do_aglwgt_deploy_append() {
	cat <<'EOF' >${D}/${sysconfdir}/agl-postinsts/99_4A_service_patch.sh
N=600
svcfile="/usr/local/lib/systemd/*/afm-service-agl-service-audio-4a*.service"
set -x
echo "-- TMP 4A INSTALL FIX from meta-agl/meta-app-framework/recipes-multimedia/agl-service-audio-4a/agl-service-audio-4a_git.bb - MUST BE REMOVED !!!"
while ! ls $svcfile > /dev/null; do
	if [ $N = 0 ]; then echo "-- TMP 4A INSTALL NOT FIXED"; exit 0; fi
	echo .
	sleep 0.2
	N=$(expr $N - 1)
done
sed -i '/\[Unit\]/ a Before=pulseaudio.service' $svcfile;
sed -i '/\[Unit\]/ a ConditionPathExistsGlob=/dev/snd/control*' $svcfile;
sed -i '/ExecStartPre=/ a Environment=LIBASOUND_THREAD_SAFE=0' $svcfile;

sed -i -e 's|/usr/bin/afb-daemon\>|& --ldpath=/usr/libexec/agl/4a-alsa-core/lib:/usr/libexec/agl/4a-hal/lib:/usr/libexec/agl/smixer/lib|' $svcfile

# binder name matters: it must match "afbd-4a-*" => the config file (controller json file) that will be searched will be "policy-4a-*.json"
sed -i -e 's|--name afbd-agl-\(.*\)|--name afbd-4a-\1|' $svcfile

# workaround for SPEC-1762
sed -i -e 's|/usr/bin/afb-daemon\>|/usr/bin/4a_wait_bt.sh &|' $svcfile

echo "-- TMP 4A INSTALL FIX END"

EOF
	chmod a+x ${D}/${sysconfdir}/agl-postinsts/99_4A_service_patch.sh
}

##############################################
# workaround for SPEC-1762/SPEC-1763
RDEPENDS_${PN} += "bash"
SRC_URI += "file://4a_wait_bt.sh"
do_install_append() {
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/4a_wait_bt.sh ${D}${bindir}/
}
#
##############################################
