#!/bin/bash

. /etc/platform-info/hardware

# example for h3ulcb+kf:
# HW_CPU_COUNT=8
# HW_BLUETOOTH_DEVICES="hci0"
# HW_SOC_FAMILY="R-Car Gen3"
# HW_CPU_ARCH="aarch64"
# HW_SOC_NAME="H3"
# HW_SOC_ID="r8a7795"
# HW_CPU_COMPATIBILITY="cortex-a57 armv8"
# HW_MEMORY_TOTAL=3775
# HW_SOC_REVISION="ES2.0"
# HW_ETHERNET_DEVICES="dummy0 eth0"
# HW_CAN_DEVICES="can0 can1"
# HW_BOARD_MODEL="kingfisher-h3ulcb-r8a7795"
# HW_WIFI_DEVICES="wlan0"

stdout_in_terminal=0
[[ -t 1 ]] && stdout_in_terminal=1

function color {
	[[ $stdout_in_terminal == 0 ]] && return
	for k in $*; do
		case $k in
			bold) tput bold 2>/dev/null;;
			none) tput sgr0 2>/dev/null;;
			*) tput setaf $k 2>/dev/null;;
		esac
	done
}

color_red=$(color bold 1)
color_green=$(color bold 2)
color_yellow=$(color bold 3)
color_none=$(color none)

pass() {
	echo "${color_green}PASS${color_none}: $*"
}

fail() {
	echo "${color_red}FAIL${color_none}: $*"
}

skip() {
	echo "${color_yellow}SKIP${color_none}: $*"
}
