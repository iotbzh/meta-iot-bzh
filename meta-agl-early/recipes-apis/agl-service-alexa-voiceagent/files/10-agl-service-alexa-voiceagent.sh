#!/bin/sh -e
for file in agl-service-alexa-voiceagent.wgt; do
    /usr/bin/afm-install install /usr/AGL/apps/autoinstall/$file
done
sync

