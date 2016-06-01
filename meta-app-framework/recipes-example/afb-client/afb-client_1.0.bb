SUMMARY = "HTML5 demo template for AFB"
DESCRIPTION = "afb-client is a sample AngularJS/HTML5 application using \
Application Framework Binder with token plugin."
HOMEPAGE = "http://www.iot.bzh"

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6cb04bdb88e11107e3af4d8e3f301be5"

#DEPENDS = "nodejs-native"
RDEPENDS_${PN} = "af-binder af-binder-plugin-authlogin"

SRC_URI = "git://github.com/iotbzh/afb-client;protocol=https;branch=master \
           file://afb-client \
          "
SRCREV = "aea8e5ed904ae9334f5d3f87c5d51016990837d8"
S = "${WORKDIR}/git"

do_install () {
  mkdir -p ${D}/${datadir}/agl/afb-client
  cp -ra ${S}/dist.prod/* ${D}/${datadir}/agl/afb-client/

  mkdir -p ${D}/${bindir}
  install -m 0755 ${WORKDIR}/afb-client ${D}/${bindir}/afb-client
}

FILES_${PN} += "${datadir}"
