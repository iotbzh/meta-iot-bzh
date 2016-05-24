FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# add "protocol" attribute support to "qtwebsockets-qmlplugins",
# needed by afb-daemon's "qml_websocket_client" demo

SRC_URI += " \
           file://qtwebsockets-protocol.patch \
           "
