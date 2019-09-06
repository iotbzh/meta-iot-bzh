# Copyright (C) 2019 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license

SUMMARY     = "Platform runtime tools"
DESCRIPTION = "Collection of tools for hardware detection, devices settings, custom boot options, hotplug handling ..."
HOMEPAGE    = "https://github.com/iotbzh/platform-runtime-tools"
SECTION     = "base"

inherit cmake

SRC_URI = "gitsm://github.com/iotbzh/platform-runtime-tools.git;protocol=https"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PV = "0.1"
