# IoT.bzh Installer

This layer provides recipes to create a minimal image (call agl-image-micro)
which can embeds an image ready to be install on a board.

This is really interesting if you want to install an image on an irremovable
storage (like a nvme disk).

So you can boot the image installer from usb, ssd or over the net and easily
install an image on the board.

## Image conf

First use the official [AGL documentation](https://docs.automotivelinux.org/docs/en/master/getting_started/)

### Configure AGL image:

Add an extra feature to your
[agl setup](https://docs.automotivelinux.org/docs/en/master/getting_started/reference/getting-started/image-workflow-initialize-build-environment.html)

* agl feature: agl-installer

## Installer Configuration

* Add your image and configuration to your local conf.

```bash
echo INSTALLER_ROOTFS_ARCHIVES = \"${YOUR_IMAGE_PATH}agl-demo-platform-m3ulcb.wic.xz ${YOUR_IMAGE_PATH}agl-demo-platform-m3ulcb.wic.bmap\" >> conf/local.conf
echo INSTALLER_BOOT_FILES = \"${YOUR_IMAGE_PATH}/r8a7796-m3ulcb-kf.dtb ${YOUR_IMAGE_PATH}/Image\" >> conf/local.conf

```


* Select your storage divice


```bash
echo INSTALLER_ROOTFS_TARGET = \"nvme0n1\" >> conf/local.conf
echo INSTALLER_BOOT_TARGET = \"mmcblk0\" >> conf/local.conf
```

Note: the rootfs and the kernel+dtb can be install on different storage.

## Build image installer

```bash
bitbake agl-image-micro
```

## Installer Image

Connect to your board an exec:

```bash
/installer-config/install.sh
```

Now you remove your storage and you are ready to boot on your image.

