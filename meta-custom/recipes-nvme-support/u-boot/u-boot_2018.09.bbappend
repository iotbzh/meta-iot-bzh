#FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
#FILESEXTRAPATHS_prepend := "/xdt/UBOOT/meta/meta-iot-bzh/meta-agl-early/recipes-bsp/u-boot/uboot/:"
FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI_append = " \
    file://0001-enable-NVME-on-M3.patch \
    file://0002-activate-PCIe.patch \
"
