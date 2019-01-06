RDEPENDS_${PN} += "\
	iotbzh-base-files \
	platform-hardware-info \
"

# only for rcar platforms
RDEPENDS_${PN}_append_m3ulcb = "rcar-pvr-watchdog"
RDEPENDS_${PN}_append_h3ulcb = "rcar-pvr-watchdog"

