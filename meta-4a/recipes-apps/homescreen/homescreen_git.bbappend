SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application build with recipe method (4A version)"
HOMEPAGE    = "http://git.ovh.iot/4a/"

SRC_URI_prepend = "gitsm://git.ovh.iot/4a/homescreen.git;protocol=http;branch=${4A_BRANCH} "
SRC_URI_remove = "git://gerrit.automotivelinux.org/gerrit/apps/homescreen;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AUTOREV}"
