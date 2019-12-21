require julius_${PV}.inc

ALLOW_EMPTY_${PN} = "1"

inherit meson pkgconfig

DEPENDS += "\
   libedit \
   libsndfile1 \
   zlib \
"

PACKAGECONFIG ??= "\
   alsa \
"

PACKAGECONFIG[alsa] = "-Dlibsent:mictype=alsa,,alsa-lib"

RDEPENDS_${PN} += "perl"

FILES_${PN} += "${INSTALL_PREFIX}/dfa_minimize"
FILES_${PN} += "${INSTALL_PREFIX}/mkfa"
FILES_${PN} += "${INSTALL_PREFIX}/yomi2voca.pl"
FILES_${PN} += "${INSTALL_PREFIX}/mkdfa.pl"
FILES_${PN} += "${INSTALL_PREFIX}/mkdfa.py"
FILES_${PN} += "${INSTALL_PREFIX}/gram2sapixml.pl"

