RDEPENDS_${PN} += "\
	platform-hardware-info \
	platform-hardware-config \
	platform-devices-info \
	platform-devices-config \
	${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'platform-test', '' , d)} \
"

