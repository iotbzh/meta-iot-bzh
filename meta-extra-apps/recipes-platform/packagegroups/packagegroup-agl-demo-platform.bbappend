RDEPENDS_${PN} += " \
    agl-webbrowser \ 
    qtlocation \	
    "
    
PACKAGECONFIG_append_pn-qtlocation = " geoclue"

