do_rootfs[vardeps] += "INSTALLER_ROOTFS_ARCHIVES"
do_rootfs[vardeps] += "INSTALLER_BOOT_FILES"

do_rootfs[vardeps] += "INSTALLER_ROOTFS_TARGET"
do_rootfs[vardeps] += "INSTALLER_BOOT_TARGET"

INSTALLER_CONFIG_DIR = "installer-config"
INSTALLER_DIR = "${IMAGE_ROOTFS}/${INSTALLER_CONFIG_DIR}"

RPM_POSTPROCESS_COMMANDS_append = "agl_installer;"

agl_installer() {
    mkdir -p ${INSTALLER_DIR}
    if [ -n "${INSTALLER_ROOTFS_ARCHIVES}" ] ; then
        mkdir -p ${INSTALLER_DIR}/rootfs/
        for f in ${INSTALLER_ROOTFS_ARCHIVES}; do
            if [ ! -e ${f} ]; then
                echo "From INSTALLER_ROOTFS_ARCHIVES=${f} invalide file"
                exit 1
            fi
            cp  ${f} ${INSTALLER_DIR}/rootfs/
            DEST_ROOTFS_ARCHIVES="${DEST_ROOTFS_ARCHIVES} /${INSTALLER_CONFIG_DIR}/rootfs/$(basename ${f})"
        done
    fi
    if [ -n "${INSTALLER_BOOT_FILES}" ] ; then
        mkdir -p ${INSTALLER_DIR}/boot/
        for f in ${INSTALLER_BOOT_FILES}; do
            if [ ! -e ${f} ]; then
                echo "From INSTALLER_BOOT_FILES=${f} invalide file"
                exit 1
            fi
            cp  ${f} ${INSTALLER_DIR}/boot/
            DEST_BOOT_FILES="${DEST_BOOT_FILES} /${INSTALLER_CONFIG_DIR}/boot/$(basename ${f})"
        done
    fi
    
    cat >${INSTALLER_DIR}/install.sh <<EOF
#!/bin/bash

set -e
set -x

export INSTALLER_ROOTFS_FILES="${DEST_ROOTFS_ARCHIVES}"
export INSTALLER_BOOT_FILES="${DEST_BOOT_FILES}"
export INSTALLER_ROOTFS_TARGET="${INSTALLER_ROOTFS_TARGET}"
export INSTALLER_BOOT_TARGET="${INSTALLER_BOOT_TARGET}"
EOF

    if [ -n "${INSTALLER_ROOTFS_TARGET}" ] ; then
        cat >>${INSTALLER_DIR}/install.sh <<EOF
bmaptool copy /${INSTALLER_CONFIG_DIR}/rootfs/*.wic.xz /dev/${INSTALLER_ROOTFS_TARGET}
PARTSTART=\$(cat /sys/block/${INSTALLER_ROOTFS_TARGET}/${INSTALLER_ROOTFS_TARGET}p1/start)
# Using EOP (End Of Parameter) instead of EOF to not break the first cat.
fdisk --wipe-partitions never /dev/${INSTALLER_ROOTFS_TARGET} <<EOP
d
n
p

\${PARTSTART}

w
EOP

fsck  /dev/${INSTALLER_ROOTFS_TARGET}p1
resize2fs /dev/${INSTALLER_ROOTFS_TARGET}p1

EOF
    fi
    
    if [ -n "${INSTALLER_BOOT_TARGET}" ] ; then
        cat >>${INSTALLER_DIR}/install.sh <<EOF
# Using EOP (End Of Parameter) instead of EOF to not break the first cat.
fdisk --wipe-partitions never /dev/${INSTALLER_ROOTFS_TARGET} <<EOP
d
n
p



w
EOP

mkfs.ext4 -F /dev/${INSTALLER_BOOT_TARGET}p1
mkdir -p  /mnt/bootfs
mount /dev/${INSTALLER_BOOT_TARGET}p1 /mnt/bootfs
mkdir -p  /mnt/bootfs/boot
for f in \$INSTALLER_BOOT_FILES; do
    if [ ! -e \${f} ]; then
        echo "From INSTALLER_BOOT_FILES=\${f} invalide file"
        exit 1
    fi
    cp \$f /mnt/bootfs/boot
done
umount /mnt/bootfs
rm -fr /mnt/bootfs
EOF
    fi

chmod a+x ${INSTALLER_DIR}/install.sh

sync
#reboot
}
