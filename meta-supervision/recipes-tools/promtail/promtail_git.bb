# Copyright (C) 2019 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY     = "promtail command (syslog collector from Grafana)"
DESCRIPTION = "promtail command (syslog collector from Grafana)"
HOMEPAGE    = "https://github.com/grafana/loki"
SECTION     = "apps"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/grafana/loki;protocol=https;branch=master"
SRCREV = "${AUTOREV}"

PV = "0.1+git${SRCPV}"

GO_IMPORT = "github.com/grafana/loki"
GO_INSTALL = "${GO_IMPORT}/cmd/promtail"

inherit go

DEPENDS = "systemd"

RDEPENDS_${PN}-dev += "bash dumb-init"
