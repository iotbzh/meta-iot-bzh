# Copyright (C) 2018 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY = "AGL Demo Assets"
SECTION = "multimedia"
LICENSE = "CC0-1.0 & CC-BY-3.0 & CC-BY-NC-ND-3.0 & CC-BY-4.0 & Unsplash"

# license text downloaded from 
# https://creativecommons.org/2014/01/07/plaintext-versions-of-creative-commons-4-0-licenses/
# - https://creativecommons.org/licenses/by-nc-nd/3.0/legalcode.txt
# - https://creativecommons.org/licenses/by/4.0/legalcode.txt
LICENSE_PATH += "${THISDIR}/licences"

LIC_FILES_CHKSUM = "\
   file://${COMMON_LICENSE_DIR}/CC0-1.0;md5=0ceb3372c9595f0a8067e55da801e4a1 \
   file://${COMMON_LICENSE_DIR}/CC-BY-3.0;md5=dfa02b5755629022e267f10b9c0a2ab7 \
   file://${COMMON_LICENSE_DIR}/CC-BY-NC-ND-3.0;md5=a1038f0f4a3de1fe9e79e2180c40f10c \
   file://${THISDIR}/licenses/CC-BY-4.0;md5=3e19557ffd5cbc68a1fd63ac8d687a95 \
   file://${THISDIR}/licenses/Unsplash;md5=d468155de19c134c7abf7ea188e37861 \
"

SRC_URI = "gitsm://github.com/iotbzh/agl-demo-assets.git;protocol=https"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

#ASSETS_DIR = "${datadir}/${PN}"

# lightmediascanner currently use hardcoded paths
ASSETS_DIR = "/home/1001"

do_install_append() {
    install -d ${D}${ASSETS_DIR}
	make -C ${S} install DESTDIR=${D} PREFIX=${ASSETS_DIR}
}

FILES_${PN} += "${ASSETS_DIR}/Music/*"
FILES_${PN} += "${ASSETS_DIR}/Pictures/*"
FILES_${PN} += "${ASSETS_DIR}/Videos/*"

