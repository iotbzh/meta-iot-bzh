# Copyright (C) 2019 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "Platform runtime tools"
DESCRIPTION = "Collection of tools for hardware detection, devices settings, custom boot options, hotplug handling ..."
HOMEPAGE    = "https://github.com/iotbzh/platform-runtime-tools"
SECTION     = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "gitsm://github.com/iotbzh/platform-runtime-tools.git;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

PV = "0.1"

S = "${WORKDIR}/git"

inherit cmake systemd

RDEPENDS_${PN} = "bash"

SYSTEMD_PACKAGES += "${PN}"
SYSTEMD_SERVICE_${PN} = "\
   platform-core-customize.service \
   platform-devices-customize.service \
   platform-core-detection.service \
   platform-devices-detection.service \
"
SYSTEMD_AUTO_ENABLE_${PN} = "enable"

EXTRA_OECMAKE="-DSYSTEM_UNIT_DIR=${systemd_system_unitdir} -DSCRIPTS_DIR=${libexecdir}/${PN} -DREGISTRY_PREFIX=agl"

FILES_${PN} += "${systemd_system_unitdir}"

