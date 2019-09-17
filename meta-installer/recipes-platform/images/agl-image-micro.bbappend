inherit agl_installer_image

IMAGE_INSTALL_append = " util-linux tar bzip2 xz e2fsprogs-resize2fs parted bmap-tools hdparm"
IMAGE_INSTALL_append = " ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'smartmontools htop iotop dstat stress-ng' , '', d)}"
