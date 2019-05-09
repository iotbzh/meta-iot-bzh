#!/bin/bash

detect_intel() {
	[[ ! "$(cat /proc/cpuinfo | grep vendor_id -m1 | grep Intel)" ]] && return 0;
	info "Intel CPU detected."

	local -A keys
	
	# soc information
	keys[soc_vendor]="Intel"
	keys[soc_family]=$(dmidecode --type processor | grep -m1 Family | awk -F": " '{print $2}')
	keys[soc_id]=$(cat /proc/cpuinfo | grep name -m1 | sed -e 's/.*(TM) \(.*\)@.*/\1/' | sed 's/CPU//g' | sed 's/ //g')
	keys[soc_name]="${keys[soc_vendor]} ${keys[soc_family]} ${keys[soc_id]}"
	keys[soc_revision]=$(cat /proc/cpuinfo | grep -m1 "stepping" | awk -F": " '{print $2}')

	# cpu information
	keys[cpu_microarch]=$(readkey /sys/devices/cpu/caps/pmu_name)
	keys[cpu_compatibility]="unknown"
	keys[cpu_freq]=$(dmidecode --type processor | grep -m1 "Max Speed" | awk -F": " '{print $2}' | awk '{print $1;}')

	for x in ${!keys[@]}; do
		addkey $x "${keys[$x]}"
	done
	
	#detect board model
	case ${keys[soc_id]} in
		E3826)
			keys[board_model]="Minnowboard Turbot"	
			lspci | grep -i "i211" > /dev/null
			if [ $? = 0 ]
			then keys[board_model]=${keys[board_model]}" B"
			else keys[board_model]=${keys[board_model]}" A"
			fi

			NB_ETH_CTL=$(echo "${keys[ethernet_devices]}" | wc -w)
			if [ $NB_ETH_CTL == 1 ]
			then keys[board_model]=${keys[board_model]}" Dual-Core"
			else keys[board_model]=${keys[board_model]}" Dual-Core Dual Ethernet"
			fi
			;;	
		E3845)
			keys[board_model]="Minnowboard Turbot B Quad-Core"		

			NB_ETH_CTL=$(echo "${keys[ethernet_devices]}" | wc -w)
			if [ $NB_ETH_CTL == 2 ]
			then keys[board_model]=${keys[board_model]}" Dual Ethernet"
			fi
			;;
		*)
			keys[board_model]="unknown"
			error "Unknown board model - couldn't determine board model"
			;;
	esac

	for x in ${!keys[@]}; do
		addkey $x "${keys[$x]}"
	done
}

detect_intel
