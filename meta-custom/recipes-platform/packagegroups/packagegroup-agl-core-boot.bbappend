RDEPENDS_${PN} += "\
	platform-runtime-tools \
	${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'platform-test', '' , d)} \
"

