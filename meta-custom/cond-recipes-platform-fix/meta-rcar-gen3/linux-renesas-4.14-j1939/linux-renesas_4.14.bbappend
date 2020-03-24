FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " \
    file://can-j1939-v4.14.75.rcar3.patch \
    file://j1939-drivers-2nd-round.patch \
    file://j1939.cfg \
"
