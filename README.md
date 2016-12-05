meta-iot-agl
============

**A collection of layers on top of meta-agl developed by iot.bzh**


## Description

**meta-iot-agl** provides a way to run HTML5 and native applications on top of AGL.

At a deeper level, it consists in an Application Framework software layer able to manage installation, deinstallation and life cycle of automotive applications.


## Installation

 2 cases here :
 - you already have your AGL image ready, and want to add the Application Framework on top of it;
 - you want a quick-and-easy way to produce an AGL image with the Application Framework built in.

### Install on top of an existing AGL image

* Grab all required Yocto layers with the following commands:
```
$ git clone https://gerrit.automotivelinux.org/gerrit/staging/meta-iot-agl
$ git clone https://github.com/01org/meta-intel-iot-security
$ cd meta-intel-iot-security; git checkout 74584c8; cd ..
```

* In your existing _"$BUILD_DIR/conf/bblayers.conf"_ file, add:
```
  /home/SDK/AGL/poky/../meta-intel-iot-security/meta-security-smack \
  /home/SDK/AGL/poky/../meta-intel-iot-security/meta-security-framework \
  /home/SDK/AGL/poky/../meta-iot-agl/meta-app-framework \
```
_("/home/SDK/AGL" being the directory containing your Yocto layers)_

* In your existing _"$BUILD_DIR/conf/local.conf"_ file, add:
```
OVERRIDES .= ":smack"
DISTRO_FEATURES_append = " smack dbus-cynara"
IMAGE_INSTALL_append = " qtwebkit qtwebkit-examples-examples"

IMAGE_CMD_TAR = "tar --xattrs-include='*'"
IMAGE_DEPENDS_tar_append = " tar-replacement-native"
EXTRANATIVEPATH += "tar-native"
```
 and also make sure you comment the following line (with a "#"):
```
#PACKAGECONFIG_remove_pn-qtquick1 = "webkit"
```

* Rebuild your image:
```
$ bitbake agl-demo-platform
```

### Install easily from scratch :

Retrieve the following document :

**http://iot.bzh/download/public/2016/bsp/AGL_Phase2-Devkit-Image_for_porter.pdf**

and follow its instructions.



## Usage

Once you have your image running, you have 2 options to install and run applications :
 - from your development station, using the Web GUI;
 - from the target, using the command line.


### From your development station (Web GUI)

Make sure your target got an IP address (latest AGL images have a DHCP client up and running).

Then, using your favorite browser, connect to it :

**http://_target_:1234/opa**

 and use the GUI buttons to install, uninstall, start and stop _.wgt_ applications.


### From the target (command line)

* Make sure the _.wgt_ applications are already on the target (you may use SSH _scp_ to transfer them, to _/home/root_ e.g.).

_(If you want sample widgets, you will find some on https://github.com/iotbzh/afm-widget-examples)_

* Install a widget:
```
$ afm-util install /home/root/annex.wgt
```
_("/home/root/annex.wgt" being the full path to the application package)_

* Verify the widget has been correctly installed:
```
$ afm-util list
```
_(should return "webapps-annex@0.0")_

* Start the widget:
```
$ afm-util start webapps-annex@0.0
```
_(application should appear on screen)_


## Known issues

* **afb-daemon** crashes repeatedly, applications do not start:
This is due to the AM/FM Radio module being incompatible with the latest binaries. To fix it:
```
$ rm -f /usr/lib/afb/radio-api.so
```
