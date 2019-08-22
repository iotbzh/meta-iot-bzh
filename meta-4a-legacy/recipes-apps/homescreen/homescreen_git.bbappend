SUMMARY     = "AGL Home Screen Application"
DESCRIPTION = "AGL Home Screen Application build with recipe method (4A version)"
HOMEPAGE    = "http://git.ovh.iot/4a-legacy/"

SRC_URI_prepend = "gitsm://git.ovh.iot/4a-legacy/homescreen.git;protocol=http;branch=4a-legacy "
SRC_URI_remove = "git://gerrit.automotivelinux.org/gerrit/apps/homescreen;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "653a8e918877096deaff9bc22290b6c69d381770"
