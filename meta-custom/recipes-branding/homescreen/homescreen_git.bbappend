FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
   file://0001-update-bottom-image-with-IoT.bzh-logo.patch \
"

# force a rebuild everytime a build is started
do_compile[nostamp] = "1"

do_configure_prepend() {
	DIST_SETUP_TOPIC="<unknown topic>"

	if [ -f "${DISTRO_SETUP_MANIFEST}" ]; then
		. ${DISTRO_SETUP_MANIFEST}
	fi

	sed -i \
		-e "s|@AGLVERSIONSTRING@|AGL v${AGLVERSION}-${MACHINE} [${DIST_SETUP_TOPIC}]|g" \
		-e "s|@AGLVERSIONSTRING2@|(${AGL_BRANCH}${IMAGE_VERSION_SUFFIX})|g" \
	${S}/homescreen/qml/images/Utility_Logo_Grey-01.svg
}

