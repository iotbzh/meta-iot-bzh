#!/bin/bash

# Copyright (C) 2018-2019 
#		Stephane Desneux <stephane.desneux@iot.bzh>
#		Ronan Le Martret <ronan.lemartret@iot.bzh>
# Released under the Apache 2.0 license

# check if btwilink must be disabled or not

# query registry for 'disable-btwilink' key
v=$(pregistry disable-btwilink)
case ${v,,} in
	1|true|on|yes)
		echo "$BASH_SOURCE: disabling btwilink module" >&2
		lsmod | grep -q  btwilink && rmmod btwilink
		echo "install btwilink /bin/false" > /etc/modprobe.d/btwilink-disable.conf
		;;
	*)
		echo "$BASH_SOURCE: enabling btwilink module" >&2
		rm -f /etc/modprobe.d/btwilink-disable.conf
		;;
esac

