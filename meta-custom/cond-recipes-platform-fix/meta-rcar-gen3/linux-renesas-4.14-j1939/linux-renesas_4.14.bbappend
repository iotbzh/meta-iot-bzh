FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

#SDX 20200511: disable J1939 patches, now incompatible with BSP 3.9.7 (meta-renesas-rcar-gen3/meta-rcar-gen3/recipes-kernel/linux/linux-renesas_4.14.bb now points to branch v4.14.75-ltsi/rcar-3.9.7 since migration to Yocto 3.1)
#SRC_URI_append = " \
#    file://can-j1939-v4.14.75.rcar3.patch \
#    file://j1939-drivers-2nd-round.patch \
#    file://j1939.cfg \
#"
