# This brings some mandatory patches for the softmixer and hal-manager.
#
# - patch 1 makes a communication library for hal-manager to get
#   the list of audio transports from bluez-alsa
# - patch 2 makes the "debug" macro always display the caller name
# - patch 3 asks dbus for a name like 'org.bluez-alsa.hciX' where hciX 
# - patch 4 is a bug fix
#   name of the HCI interface that the bluealsa daemon instance is handling
# 
# Notice that patch 2 will very likely be replaced by a full dbus interface
# in the future.
#

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=88dc1c98120259ae67b82222d7aff5c1"
SRCREV = "862a4b2cfd432444d62c00ee1394f6abd1433063"

SRC_URI += "file://0001-build-and-link-with-a-shared-library.patch"
SRC_URI += "file://0002-log-add-calling-function-name.patch"
SRC_URI += "file://0003-dbus-request-a-name-on-startup.patch"
SRC_URI += "file://0004-bluealsa-pcm-bump-the-trigger-after-pcm-prepare.patch"
