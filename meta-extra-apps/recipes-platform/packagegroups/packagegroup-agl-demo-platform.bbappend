RDEPENDS_${PN} += " \
    agl-webbrowser \
    qtlocation \
    qtlocation-qmlplugins \
    qtlocation-plugins \
    "

PACKAGECONFIG_append_pn-qtlocation = " geoclue"

