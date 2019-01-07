SUMMARY = "OLD BINDER VERSION JUST FOR HOMESCREEN AND WINDOWMANAGER AT CES19 DEMO#2"
DESCRIPTION = "DO NOT USE: THIS IS A WORKAROUND FOR SPEC-2089"

HOMEPAGE = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/src/app-framework-binder"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE-2.0.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/app-framework-binder;protocol=https;nobranch=1"

SRCREV = "refs/changes/85/18585/1"
PV = "${AGL_BRANCH}+git${SRCPV}"

S = "${WORKDIR}/git"

CFLAGS_append_agl-devel = " -DAGL_DEVEL"

EXTRA_OECMAKE_append = "\
	-DAGLVERSION=${AGLVERSION} \
"

DEPENDS = "file json-c libmicrohttpd systemd util-linux openssl cynara"

inherit cmake pkgconfig

EXTRA_OECMAKE_append_class-target = "\
	-DUNITDIR_SYSTEM=${systemd_system_unitdir} \
"

EXTRA_OECMAKE_append_agl-devel = " \
	-DAGL_DEVEL=ON \
	-DINCLUDE_MONITORING=ON \
	-DINCLUDE_SUPERVISOR=ON -DAFS_SURPERVISION_SOCKET=/run/platform/supervisor \
"

pkg_postinst_${PN}() {
	mkdir -p "$D${libdir}/afb"
}

do_install_append() {
	mv ${D}${bindir}/afb-daemon ${D}/.afb-daemon
	rm -rf ${D}/*
	install -d ${D}/alt/bin
	install -m 0755 ${D}/.afb-daemon ${D}/alt/bin/afb-daemon
	rm -f ${D}/.afb-daemon

	# generate a firstboot fragment to overwrite binder in some services
	install -d ${D}${sysconfdir}/agl-postinsts
	cat <<'EOF' >>${D}/${sysconfdir}/agl-postinsts/99_af-binder-alt.sh
srcdir=/var/local/lib/systemd/system/
for svc in \
	windowmanager-service \
	homescreen-service \
	; do
    for x in $srcdir/afm-*-$svc--*@.service; do
        echo "TO BE REMOVED AFTER CES19 - SPEC-2089: using alternate afb-daemon for $x"
        sed -i -e 's|/usr/bin/afb-daemon|/alt/bin/afb-daemon|g' $x
    done
done
EOF
	chmod a+x ${D}/${sysconfdir}/agl-postinsts/99_af-binder-alt.sh
}

FILES_${PN} += "/alt/bin/afb-daemon"



