#!/bin/bash

detect_arch() {
	local -A keys

	keys[cpu_arch]="unknown"
	[[ "$(grep MODALIAS /sys/devices/system/cpu/cpu0/uevent)" =~ :type:([^:]+):.*$ ]] && {
		keys[cpu_arch]=$(uname -i)
	}

	addkey cpu_arch "${keys[cpu_arch]}"

	#do specific arch detection here
}

detect_arch
