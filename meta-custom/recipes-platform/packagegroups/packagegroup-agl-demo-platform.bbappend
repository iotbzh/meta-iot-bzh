# disable btwilink on h3ulcb too

DEMO_PLATFORM_CONF_append_h3ulcb = "${@bb.utils.contains("DEMO_ENABLE_BTWILINK", "true", "", " btwilink-disable-conf", d)}"
