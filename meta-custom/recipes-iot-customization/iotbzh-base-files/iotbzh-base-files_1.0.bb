SUMMARY = "IoT.bzh custom config scripts and files"
DESCRIPTION = "The package contains come customization files (bashrc, vimrc ...) to enhance default environment."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "\
    file://bashrc \
    file://vimrc \
"

# skip useless tasks
do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    install -d ${D}${sysconfdir}/profile.d
    install -m 0755 ${WORKDIR}/bashrc ${D}${sysconfdir}/profile.d/iotbzh.sh
    install -m 0644 ${WORKDIR}/vimrc ${D}${sysconfdir}/vimrc
}

RDEPENDS_${PN} = "bash vim grep"
