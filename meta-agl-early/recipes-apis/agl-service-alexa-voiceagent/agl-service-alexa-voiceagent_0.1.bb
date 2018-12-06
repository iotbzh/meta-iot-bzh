SUMMARY = "Alexa voice agent"
DESCRIPTION = "Alexa voice agent"
LICENSE = "CLOSED"
SECTION = "apps"

SRC_URI = "file://agl-service-alexa-voiceagent.wgt \
    file://AlexaAutoCoreEngineConfig.json \
    file://10-agl-service-alexa-voiceagent.sh \
"

PV = "${AGLVERSION}"

do_install() {
        install -d ${D}/usr/AGL/apps/autoinstall
        install -m 0644 ${WORKDIR}/agl-service-alexa-voiceagent.wgt ${D}/usr/AGL/apps/autoinstall

        install -d ${D}/opt/AGL/alexa-voiceagent-service/var/config
        install -m 0644 ${WORKDIR}/AlexaAutoCoreEngineConfig.json ${D}/opt/AGL/alexa-voiceagent-service/var/config

	install -d ${D}/etc/agl-postinsts
	install -m 0755 ${WORKDIR}/10-agl-service-alexa-voiceagent.sh ${D}/etc/agl-postinsts
}

FILES_${PN} += "/usr/AGL/apps/autoinstall/* /opt/AGL/alexa-voiceagent-service/var/config/* /etc/agl-postinsts/*"
