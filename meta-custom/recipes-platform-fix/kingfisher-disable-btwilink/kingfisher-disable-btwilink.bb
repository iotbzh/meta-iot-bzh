# Copyright (C) 2018-2019 
#		Stephane Desneux <stephane.desneux@iot.bzh>
#		Ronan Le Martret <ronan.lemartret@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "Detect and disable btwilink (Kingfisher bluetooth module)"
DESCRIPTION = "Uses platform-hardware-config to detect conditions for disabling Kingfisher onboard BT controller"
HOMEPAGE    = "https://www.automotivelinux.org/"
SECTION     = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PV = "0.1"

SRC_URI = "\
   file://btwilink-disable.sh \
"

inherit platform-runtime-tools

PLATFORM_RUNTIME_TOOLS_IDS = "btwilink"

PLATFORM_RUNTIME_TOOLS_STEP_btwilink = "core"
PLATFORM_RUNTIME_TOOLS_ADD_btwilink = "${WORKDIR}/btwilink-disable.sh"
PLATFORM_RUNTIME_TOOLS_WHEN_btwilink = "vendor/renesas"
PLATFORM_RUNTIME_TOOLS_PRIORITY_btwilink = "5"

