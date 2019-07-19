RDEPENDS_${PN} += "\
	${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'netdata', '' , d)} \
"

