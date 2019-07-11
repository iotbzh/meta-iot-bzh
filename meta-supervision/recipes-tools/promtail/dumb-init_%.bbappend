# Stephane Desneux <sdx@iot.bzh> 20190711: 
# this is needed for promtail-dev package QA to be happy
# some docker entrypoint scripts need /usr/bin/dumb-init, but package dumb-init provides /sbin/dumb-init

do_install_append() {
	install -d ${D}${bindir}
	lnr ${D}${base_sbindir}/dumb-init ${D}${bindir}/dumb-init
}
