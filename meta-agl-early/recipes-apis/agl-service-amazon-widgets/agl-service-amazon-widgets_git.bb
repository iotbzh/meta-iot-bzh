SUMMARY = "Amazon widgets"
DESCRIPTION = "alexa voice agent, alexa ics"
LICENSE = "CLOSED"
SECTION = "apps"

SRC_URI = "git://github.com/iotbzh/alexa-widgets.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

PV = "${AGLVERSION}"
S  = "${WORKDIR}/git"

WIDGETS_LIST = "alexa-voiceagent-service ics-alexa-app"

RDEPENDS_${PN} += " \
    qtlocation \
    qtlocation-qmlplugins \
    qtlocation-plugins \
    "

PACKAGECONFIG_append_pn-qtlocation = " geoclue"

do_install() {

    install -d ${D}/usr/AGL/apps/autoinstall
	install -d ${D}/etc/agl-postinsts

    set -x
    for WIDGET in ${WIDGETS_LIST}; do
        echo ${WIDGET}
        install -m 0644 ${S}/${WIDGET}.wgt ${D}/usr/AGL/apps/autoinstall


        cat <<EOF >${WORKDIR}/10-${WIDGET}.sh
#!/bin/sh -e
for file in ${WIDGET}.wgt; do
    /usr/bin/afm-install install /usr/AGL/apps/autoinstall/\$file
done
sync
EOF
	    install -m 0755 ${WORKDIR}/10-${WIDGET}.sh ${D}/etc/agl-postinsts
    done
}

FILES_${PN} += "/usr/AGL/apps/autoinstall/* /etc/agl-postinsts/*"
