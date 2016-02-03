# NOTE: using libcap-native and setcap in install doesn't work
# NOTE: there is no SYSTEMD_USER_SERVICE_...
# NOTE: maybe setting afm_name to agl-framework is cleaner but has implications
# NOTE: there is a hack of security for using groups and dbus (to be checked)
# NOTE: using ZIP programs creates directories with mode 777 (very bad)

inherit cmake pkgconfig useradd systemd

SUMMARY = "AGL Framework Master, main part"
DESCRIPTION = "\
This is a core framework component for managing \
applications, widgets, and components. \
"

HOMEPAGE = "http://iotbzh.github.io/afm-main"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/iotbzh/afm-main;protocol=https;branch=master \
	file://webkit-workaround.patch \
"
SRCREV = "507b8b43ed0768a8edafc21760184ee600ddafe6"

SECTION = "base"

S = "${WORKDIR}/git"

DEPENDS = "openssl libxml2 xmlsec1 dbus zip unzip json-c security-manager libcap"
RDEPENDS_${PN} = "libcap-bin"

#afm_name    = "agl-framework"
afm_name    = "afm"
afm_confdir = "${sysconfdir}/${afm_name}"
afm_datadir = "${datadir}/${afm_name}"

EXTRA_OECMAKE = "\
	-DUSE_LIBZIP=0 \
	-DUSE_SIMULATION=0 \
	-Dafm_name=${afm_name} \
	-Dafm_confdir=${afm_confdir} \
	-Dafm_datadir=${afm_datadir} \
	-DUNITDIR_USER=${systemd_user_unitdir} \
	-DUNITDIR_SYSTEM=${systemd_system_unitdir} \
"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-g ${afm_name} -d ${afm_datadir} -r ${afm_name}"
GROUPADD_PARAM_${PN} = "-r ${afm_name}"

SYSTEMD_SERVICE_${PN} = "afm-system-daemon.service"

FILES_${PN} += "\
	${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_user_unitdir}/afm-user-daemon.service', '', d)} \
"

RDEPENDS_${PN}_append_smack = " smack-userspace libcap"
DEPENDS_append_smack = " smack-userspace-native"

# short hack here
SRC_URI += " file://Hack-to-allow-the-debugging.patch"

do_install_append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        mkdir -p ${D}${sysconfdir}/systemd/user/default.target.wants
        ln -sf ${systemd_user_unitdir}/afm-user-daemon.service ${D}${sysconfdir}/systemd/user/default.target.wants
    fi
}

pkg_postinst_${PN}() {
    #!/bin/sh

    # avoid to run on host
    [ x"$D" != "x" ] && exit 1

    mkdir -p $D${afm_datadir}/applications $D${afm_datadir}/icons
    setcap cap_mac_override,cap_mac_admin,cap_setgid=ie $D${bindir}/afm-user-daemon
    exit 0
}

pkg_postinst_${PN}_smack() {
    #!/bin/sh

    # avoid to run on host
    [ x"$D" != "x" ] && exit 1

    mkdir -p $D${afm_datadir}/applications $D${afm_datadir}/icons
    chown ${afm_name}:${afm_name} $D${afm_datadir} $D${afm_datadir}/applications $D${afm_datadir}/icons
    chsmack -a 'System::Shared' -t $D${afm_datadir} $D${afm_datadir}/applications $D${afm_datadir}/icons
    setcap cap_mac_override,cap_mac_admin,cap_setgid=ie $D${bindir}/afm-user-daemon
    exit 0
}

BBCLASSEXTEND = "native nativesdk"

