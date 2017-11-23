DESCRIPTION = "The support libraries and tools for developper kit"
LICENSE = "MIT"

inherit packagegroup

PACKAGES = "packagegroup-agl-devkit"

ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} += "\
	lua \
"

