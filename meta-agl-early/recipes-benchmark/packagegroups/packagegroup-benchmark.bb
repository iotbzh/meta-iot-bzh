DESCRIPTION = "Benchmark testing packages"

LICENSE = "MIT"

inherit packagegroup

PACKAGES = " \
    packagegroup-benchmark \
"

RDEPENDS_packagegroup-benchmark = " \
    stress-ng \
    glmark2 \
	dmidecode \
    pciutils \
    platform-benchmark \
"
