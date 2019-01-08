# set root password if not empty

# NB: avoir quotes ['"] in password as it breaks usermod call and escaping is 
# a bit complex
ROOT_PASSWORD ?= "iot.bzh"

inherit extrausers
EXTRA_USERS_PARAMS += "${@('usermod -P \"%s\" root;' % d.getVar('ROOT_PASSWORD')) if (d.getVar('ROOT_PASSWORD') != '') else ''}"
