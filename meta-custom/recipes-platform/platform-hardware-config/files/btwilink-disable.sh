#!/bin/bash

# Copyright (C) 2018-2019 
#		Stephane Desneux <stephane.desneux@iot.bzh>
#		Ronan Le Martret <ronan.lemartret@iot.bzh>
# Released under the Apache 2.0 license

lsmod | grep -q  btwilink && rmmod btwilink

echo "install btwilink /bin/false" > /etc/modprobe.d/btwilink-disable.conf
