# extra userdata definition needed by monitoring recipes
require recipes-monitoring/extra.conf.inc

# remove useless tarball images
IMAGE_FSTYPES_remove = "tar.xz"
IMAGE_FSTYPES_remove = "tar.bz2"
IMAGE_FSTYPES_remove = "tar.gz"


