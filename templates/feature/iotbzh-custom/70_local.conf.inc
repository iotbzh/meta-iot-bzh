# extra userdata definition needed by monitoring recipes
require recipes-monitoring/extra.conf.inc

# remove useless tarball images
IMAGE_FSTYPES_remove = "tar.xz"
IMAGE_FSTYPES_remove = "tar.bz2"
IMAGE_FSTYPES_remove = "tar.gz"

# enable support for RPI v2 camera
KF_ENABLE_IMX219 = "1"


