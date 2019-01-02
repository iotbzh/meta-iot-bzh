FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " \
file://0001-Speech-Chrome-changes-integrated-into-homescreen.patch \
file://0002-remove-require-api-to-vshl-to-add-when-fix-bug.patch \
file://bar.png \
file://push_to_talk.png \
"

S  = "${WORKDIR}/git"

do_configure_prepend() {
    install -m 0644 ${WORKDIR}/bar.png ${S}/homescreen/qml/images/SpeechChrome
    install -m 0644 ${WORKDIR}/push_to_talk.png ${S}/homescreen/qml/images/SpeechChrome
}
