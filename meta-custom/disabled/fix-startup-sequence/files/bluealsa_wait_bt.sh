#!/bin/bash

# Signed-off-by: Stephane Desneux <stephane.desneux@iot.bzh>

# time from script startup
LIMIT=180
ts0=0
function ts() { echo $(( $(date +%s) - ts0 )); }
ts0=$(ts)
function havetime() { [[ $(ts) -le ${1:-$LIMIT} ]] || return 1; }

function waitloop() {
	# ensure bt modules are loaded (delay: 5s)
	while havetime 5; do
		[[ -d /sys/module/bluetooth ]] && {
			echo "bluetooth kernel module detected"
			break
		}
		echo "waiting for bluetooth kernel module to be up"
		sleep 0.2
	done
	havetime 5 || { echo "TIMEOUT REACHED"; return 1; }

	# check that we have at least one controller
	HCIDEV=
	while havetime 120; do
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
	havetime 120 || { echo "TIMEOUT REACHED"; return 1; }

	# wait for controller to be up and running
	while havetime; do
		state=$(hciconfig $HCIDEV | grep -A 2 ^hci0 | tail -1)
		[[ $state =~ UP ]] && [[ $state =~ RUNNING ]] && {
			echo "HCI controller $HCIDEV state: $state"
			break
		}
		echo "HCI controller $HCIDEV state: $state ... waiting for UP RUNNING"
		sleep 0.2
	done
	echo "HCI device up and running after $(ts) seconds"

	echo "bluealsa now starting..."
}

waitloop 2>&1 | sed 's/^/BLUEALSAWAITBT /' >&2
exec "$@"

