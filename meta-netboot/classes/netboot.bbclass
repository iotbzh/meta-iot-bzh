# Enable network bootable image and initrd/initramfs

# add image classes for uboot
IMAGE_CLASSES += "${@'image_types_uboot' if (d.getVar("KERNEL_IMAGETYPE", True) == "uImage") else ''}"

python () {
	d.appendVar("IMAGE_FSTYPES"," ext4")

	if (bb.utils.contains("IMAGE_FSTYPES","live",True,False,d)):
		# typical case for Minnowboard Max
		d.setVar("INITRD_IMAGE","initramfs-netboot-image")
		d.setVar("INITRD","%s/%s-%s.ext4.gz" % (
			d.getVar("DEPLOY_DIR_IMAGE",True),
			d.getVar("INITRD_IMAGE",True),
			d.getVar("MACHINE",True)
		))
	else:
		d.appendVar("INITRAMFS_IMAGE"," initramfs-netboot-image")
		if (d.getVar("KERNEL_IMAGETYPE",True) == "uImage"):
			# case for "old" u-boot images, like Porter board
			d.appendVar("INITRAMFS_FSTYPES"," ext4.gz.u-boot");
		else:
			# case for new u-boot images which don't require uImage format 
			d.appendVar("INITRAMFS_FSTYPES"," ext4.gz");
}

