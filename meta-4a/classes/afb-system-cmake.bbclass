inherit afb-system

DEPENDS += "alsa-lib json-c systemd af-binder cmake-apps-module-native"
inherit cmake pkgconfig

EXTRA_OECMAKE_append = " -DCMAKE_INSTALL_PREFIX=${INSTALL_PREFIX}"
