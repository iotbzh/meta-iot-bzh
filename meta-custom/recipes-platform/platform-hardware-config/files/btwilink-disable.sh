#!/bin/bash

lsmod | grep -q  btwilink && rmmod btwilink

echo "install btwilink /bin/false" > /etc/modprobe.d/btwilink-disable.conf
