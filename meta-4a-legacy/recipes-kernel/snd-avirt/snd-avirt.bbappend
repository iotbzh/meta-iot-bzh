FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

def prep_version (d) :
        KV = d.getVar('KERNEL_VERSION', True)
        if not KV :
                return "4.12"
        else:
                return KV

ORIG_KERN_VER = "${@prep_version(d)}"

VANILLA_KERNEL_VERSION = "${@str(ORIG_KERN_VER.split("-")[0].split(".")[0]+ORIG_KERN_VER.split("-")[0].split(".")[1])}"

APPLY_0001 = "${@str('no' if ${VANILLA_KERNEL_VERSION} > 412 else 'yes')}"
APPLY_0002 = "${@str('no' if ${VANILLA_KERNEL_VERSION} > 49 else 'yes')}"

SRC_URI_append = " \
            file://0001-snd-avirt-backport-kernel-4.12-api.patch;apply=${APPLY_0001} \
            file://0002-snd-avirt-backport-kernel-4.9-api.patch;apply=${APPLY_0002} \
            "
# Make sure we can expose KERNEL_VERSION ...
do_patch[depends] += "virtual/kernel:do_populate_sysroot"
