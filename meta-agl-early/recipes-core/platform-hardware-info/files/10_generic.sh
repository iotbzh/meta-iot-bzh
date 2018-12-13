#!/bin/bash

detect_netifaces() {
	[[ ! -d /sys/class/net ]] && { error "Unable to find /sys/class/net"; return 1; }

	local can=()
	local eth=()
	local wifi=()

	for x in $(ls -d /sys/class/net/* 2>/dev/null); do
		iface=$(basename $x)
		type=$(cat $x/type)

		case $type in
			1)
				# ethernet/bridge/wlan/...
				if [[ -d $x/wireless ]]; then
					wifi+=($iface)
				else
					eth+=($iface)
				fi
				;;
			280)
				# can
				can+=($iface)
				;;
			772)
				# localhost
				;;
			65534)
				# tun
				;;
			*)
				;;
		esac
	done

	addkey can_devices "${can[@]}"
	addkey ethernet_devices "${eth[@]}"
	addkey wifi_devices "${wifi[@]}"
}

detect_bluetooth() {
	local bt=()
	for x in $(ls -d /sys/class/bluetooth/* 2>/dev/null); do
		bt+=($(basename $x))
	done
	addkey bluetooth_devices "${bt[@]}"
}

detect_cpu() {
	addkey "cpu_count" $(getconf _NPROCESSORS_CONF)
}

detect_memory() {
	# total mem in MB
	addkey "memory_total" $(( $(getconf _PHYS_PAGES) * $(getconf PAGESIZE) / (1024*1024) ))
}

detect_netifaces
detect_bluetooth
detect_cpu
detect_memory

