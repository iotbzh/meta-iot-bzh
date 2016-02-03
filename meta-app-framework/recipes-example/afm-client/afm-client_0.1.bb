SUMMARY = "Sample client for AFM to install/start/stop/remove applications"
DESCRIPTION = "afm-client is a sample AngularJS/HTML5 application using \
Application Framework Manager to install, start, stop, or remove \
applications provided as .wgt widget packages."
HOMEPAGE = "http://www.iot.bzh"

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6cb04bdb88e11107e3af4d8e3f301be5"

#DEPENDS = "nodejs-native"
RDEPENDS_${PN} = "afm-main afb-daemon afb-daemon-plugin-afm-main afb-daemon-plugin-samplepost afb-daemon-plugin-token"

SRC_URI = "git://github.com/iotbzh/afm-client;protocol=https;branch=master \
           file://afm-client \
          "
SRCREV = "af7ac34045dbb46735715483c9b3bc6b7463bf53"
S = "${WORKDIR}/git"

do_install () {
  mkdir -p ${D}/${datadir}/agl/afm-client
  cp -ra ${S}/dist.prod/* ${D}/${datadir}/agl/afm-client/

  mkdir -p ${D}/${bindir}
  install -m 0755 ${WORKDIR}/afm-client ${D}/${bindir}/afm-client
}

FILES_${PN} += "${datadir}"
