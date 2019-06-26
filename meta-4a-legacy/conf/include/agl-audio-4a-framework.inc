# In order to enable the audio-4a-framework features ,
# enables the following line:
#
DISTRO_FEATURES_append = " agl-audio-4a-framework "

# asound.conf is provided by the 4a-hal-config (and not by asound.state)
#VIRTUAL-RUNTIME_alsa-state = "4a-hal-config"
VIRTUAL_RUNTIME_alsa-state = ""
# additionally there is a bug in alsa-utils-alsactl where it pulls in alsa-state directly
RRECOMMENDS_pn-alsa-utils-alsactl = "VIRTUAL-RUNTIME_alsa-state"

# why should a config package have -dev ... I'll leave that answer to you.
PACKAGES_pn-alsa-state_remove = "alsa-state-dev"
PACKAGES_pn-alsa-state_remove = "alsa-states-dev"
PACKAGES_pn-alsa-state = "alsa-state alsa-states"

PREFERRED_PROVIDER_virtual/mixer ?= "4a-mixer"
PREFERRED_RPROVIDER_virtual/mixer ?= "4a-mixer"

PREFERRED_VERSION_alsa-lib           = "1.1.6"
PREFERRED_VERSION_alsa-plugins       = "1.1.6"
PREFERRED_VERSION_alsa-tools         = "1.1.6"
PREFERRED_VERSION_alsa-utils         = "1.1.6"
PREFERRED_VERSION_alsa-utils-scripts = "1.1.6"
