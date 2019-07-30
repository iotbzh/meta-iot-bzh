FILESEXTRAPATHS_append := ":${THISDIR}/${PN}"

SRC_URI += "file://naviconfig.ini"

do_install_append() {
    install -D -m 644 ${WORKDIR}/naviconfig.ini ${D}${sysconfdir}/naviconfig.ini
}

