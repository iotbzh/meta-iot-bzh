import QtQuick 2.1
import QtQuick.Controls 1.1
//import QtWebEngine 1.1
import QtWebKit 3.0

ApplicationWindow {
	width: 1024
	height: 768
	visible: true
//	WebEngineView {
	WebView {
		url: Qt.application.arguments[1]
		anchors.fill: parent
	}
}
