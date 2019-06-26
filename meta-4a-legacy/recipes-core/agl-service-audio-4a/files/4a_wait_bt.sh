#!/bin/bash

###############################################################
# This is a workaround for SPEC-1762/SPEC-1763
#
# THIS SCRIPT MUST BE REMOVED ONCE THE ABOVE ISSUES ARE SOLVED
#
# Source recipe is:
#
# meta-audio-4a-framework/
#	recipes-multimedia/
#		agl-service-audio-4a/
#			agl-service-audio-4a_git.bb
#
# Signed-off-by: Stephane Desneux <stephane.desneux@iot.bzh>
###############################################################

# time from script startup
LIMIT=20
ts0=0
function ts() { echo $(( $(date +%s) - ts0 )); }
ts0=$(ts)
function havetime() { [[ $(ts) -le ${1:-$LIMIT} ]] || return 1; }

function waitloop() {
	# ensure bt modules are loaded (delay: 5s)
	while havetime 4; do
		[[ -d /sys/module/bluetooth ]] && {
			echo "bluetooth kernel module detected"
			break
		}
		echo "waiting for bluetooth kernel module to be up"
		sleep 0.2
	done
	havetime 4 || { echo "TIMEOUT REACHED"; return 1; }

	# check that we have at least one controller
	HCIDEV=
	while havetime 8; do
		echo "detecting hci devices..."
		for x in $(ls /sys/class/bluetooth/hci* 2>/dev/null); do
			[[ -z "$HCIDEV" ]] && HCIDEV=$(basename $x)
		done
		[[ -n "$HCIDEV" ]] && {
			echo "found HCI controller: $HCIDEV"
			break
		}
		sleep 0.2
	done
	havetime 8 || { echo "TIMEOUT REACHED"; return 1; }

	# wait for controller to be up and running
	while havetime 15; do
		state=$(hciconfig $HCIDEV | grep -A 2 ^hci0 | tail -1)
		[[ $state =~ UP ]] && [[ $state =~ RUNNING ]] && {
			echo "HCI controller $HCIDEV state: $state"
			break
		}
		echo "HCI controller $HCIDEV state: $state ... waiting for UP RUNNING"
		sleep 0.2
	done
	havetime 15 || { echo "TIMEOUT REACHED"; return 1; }
	echo "HCI device up and running after $(ts) seconds"

	# wait for bluetooth-service to return something
	while havetime; do
		res=$(afb-client-demo -d unix:/run/user/$UID/apis/ws/Bluetooth-Manager adapter_state true)
		[[ "$res" =~ \"response\":(.*)}$ ]] && res=${BASH_REMATCH[1]}
		[[ "$res" =~ \"powered\":true ]] && {
			echo "Bluetooth-Manager/power: $res"
			break
		}
		echo "Bluetooth-Manager/power: not ready yet ($res)"
		sleep 0.2
	done
	echo "Bluetooth-Manager ready after $(ts) seconds"

	echo "4A now starting..."
}

waitloop 2>&1 | sed 's/^/4AWAITBT /' >&2
exec "$@"

