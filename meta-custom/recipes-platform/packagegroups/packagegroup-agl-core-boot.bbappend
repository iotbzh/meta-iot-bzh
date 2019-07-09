RDEPENDS_${PN} += "\
	platform-hardware-info \
	platform-hardware-config \
	platform-device-info \
	platform-device-config \
	${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'platform-test', '' , d)} \
"

