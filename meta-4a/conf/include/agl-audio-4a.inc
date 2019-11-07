# In order to enable the audio-4a-framework features ,
# enables the following line:
#
DISTRO_FEATURES_append = " agl-audio-4a-framework "
DISTRO_FEATURES_remove = "pipewire"
DISTRO_FEATURES_remove = "pulseaudio"

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

# enable avirt ?
#DISTRO_FEATURES_append = " agl-audio-4a-with-avirt"

# branch to be used in 4A git repos
4A_BRANCH = "4a-next"
OVERRIDES .= ":4a"
