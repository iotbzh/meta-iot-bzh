require julius_${PV}.inc

ALLOW_EMPTY_${PN} = "1"

inherit meson native

DEPENDS += "\
"

# only build grammar tools
MESON_SOURCEPATH = "${S}/gramtools"

RDEPENDS_${PN} += "perl"

FILES_${PN} += "${INSTALL_PREFIX}/dfa_minimize"
FILES_${PN} += "${INSTALL_PREFIX}/mkfa"
FILES_${PN} += "${INSTALL_PREFIX}/yomi2voca.pl"
FILES_${PN} += "${INSTALL_PREFIX}/mkdfa.pl"
FILES_${PN} += "${INSTALL_PREFIX}/mkdfa.py"
FILES_${PN} += "${INSTALL_PREFIX}/gram2sapixml.pl"

