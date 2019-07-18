#!/bin/bash

function sighandler() {
    echo "sighandler called"
    exit 0
}

trap "sighandler" STOP INT TERM QUIT

while true
do
	:
done
