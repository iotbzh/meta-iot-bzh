RDEPENDS_${PN} += "\
	platform-runtime-tools \
	platform-e3-emulation \
	${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'platform-test', '' , d)} \
"

