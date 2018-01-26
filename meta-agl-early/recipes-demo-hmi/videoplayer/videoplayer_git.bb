SUMMARY     = "Video Player for CES2018 AGL Demonstration"
DESCRIPTION = "AGL HMI Application for demonstrating Video Player on AGL Distribution"
HOMEPAGE    = "https://gerrit.automotivelinux.org/gerrit/#/admin/projects/apps/videoplayer"
SECTION     = "apps"

LICENSE     = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ae6497158920d9524cf208c09cc4c984"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/videoplayer;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AUTOREV}"

PV = "1.0+git${SRCPV}"
S  = "${WORKDIR}/git"

# build-time dependencies
DEPENDS += "sqlite3 \
            qtquickcontrols2 \
            qtmultimedia \
            virtual/libhomescreen \
            qlibwindowmanager \
"

inherit qmake5 aglwgt

RDEPENDS_${PN} += "qtmultimedia \
                   qtmultimedia-qmlplugins \
                   agl-service-mediascanner"
