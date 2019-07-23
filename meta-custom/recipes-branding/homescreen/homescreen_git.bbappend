FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
   file://0001-update-bottom-image-with-IoT.bzh-logo.patch \
"

# force a rebuild everytime a build is started
do_compile[nostamp] = "1"

do_configure_prepend() {
	sed -i 's|@AGLVERSIONSTRING@|AGL v${AGLVERSION}-${MACHINE} (${AGL_BRANCH}${IMAGE_VERSION_SUFFIX})|g' ${S}/homescreen/qml/images/Utility_Logo_Grey-01.svg
}

