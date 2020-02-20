# ##################################################################################
#
# platform-runtime-tools bbclass
#
# Copyright (C) 2019 Stephane Desneux <stephane.desneux@iot.bzh>
# Released under the Apache 2.0 license
#
# ##################################################################################
#
# This is a helper class to package scriptlets to be launched
# by pr-customize tool
#
# Variables for the recipes:
#
# - PLATFORM_RUNTIME_TOOLS_IDS: list of ids to iterate on
#   usually, using ${PN} is fine
#
# - PLATFORM_RUNTIME_TOOLS_ADD_<id>: what script(s) to install for the given id
#   if multiple scripts are given, they will be all installed
#
# - PLATFORM_RUNTIME_TOOLS_STEP_<id>: at which step should it be launched ('core', 'devices',...). Default: 'core'
#
# - PLATFORM_RUNTIME_TOOLS_WHEN_<id>: what condition(s) to apply for the scripts of the given id
#   the values are related to runtime hardware detection:
#      'common': always true (no specific condition)
#      'vendor/xxxx': true when vendor is 'xxxx' (example: vendor/renesas vendor/intel vendor/ti ...)
#      'arch/xxxx': true when CPU architecture is 'xxxx' (example: arch/aarch64 arch/x86-64 ...)
#      'board/xxxx': true when detected board is 'xxxx' (example: kingfisher-h3ulcb-r8a7795 minnowboard-turbot ...)
#   when multiple conditions are given, a logical OR is achieved: this means that the scripts will be triggered
#   for every satisfied condition (so a script may be called multiple times)
#
# - PLATFORM_RUNTIME_TOOLS_PRIORITY_<id>: what priority to apply to the scripts. Value must be in [0,99] - 0=highest 99=lowest
#
# - PLATFORM_RUNTIME_TOOLS_FIRSTBOOT_<id>: defines if script must be run only at firstboot
#
# Here is a typical usage in a recipe:
# --------------------------------------------------------------
# SRC_URI = "file://myscript.sh"
#
# inherit platform-runtime-tools
#
# PLATFORM_RUNTIME_TOOLS_IDS = "${PN}"
# PLATFORM_RUNTIME_TOOLS_ADD_${PN} = "${WORKDIR}/myscript.sh"
# PLATFORM_RUNTIME_TOOLS_STEP_${PN} = "core"
# PLATFORM_RUNTIME_TOOLS_WHEN_${PN} = "vendor/renesas"
# PLATFORM_RUNTIME_TOOLS_PRIORITY_${PN} = "50"
# --------------------------------------------------------------
# This will install the script "myscript.sh" into folder /usr/libexec/platform-runtime-tools/customize/core/vendor/renesas/50_myscript.sh
#
# And a more complex example with multiple scripts:
# --------------------------------------------------------------
# SRC_URI = "file://script1.sh file://script2.sh file://script3.sh"
#
# inherit platform-runtime-tools
#
# PLATFORM_RUNTIME_TOOLS_IDS = "${PN}1 ${PN}2 ${PN}3"
#
# PLATFORM_RUNTIME_TOOLS_ADD_${PN}1 = "${WORKDIR}/script1.sh"
# PLATFORM_RUNTIME_TOOLS_STEP_${PN}1 = "core" 
# PLATFORM_RUNTIME_TOOLS_WHEN_${PN}1 = "arch/x86_64 vendor/renesas"  # meaning: when arch is x86_64 OR vendor is renesas
# PLATFORM_RUNTIME_TOOLS_PRIORITY_${PN}1 = "25"
#
# PLATFORM_RUNTIME_TOOLS_ADD_${PN}2 = "${WORKDIR}/script2.sh"
# PLATFORM_RUNTIME_TOOLS_STEP_${PN}2 = "core" 
# PLATFORM_RUNTIME_TOOLS_WHEN_${PN}2 = "board/kingfisher-h3ulcb-r8a7795 vendor/renesas" 
# PLATFORM_RUNTIME_TOOLS_PRIORITY_${PN}2 = "45"
# PLATFORM_RUNTIME_TOOLS_FIRSTBOOT_${PN}2 = "1"
#
# PLATFORM_RUNTIME_TOOLS_ADD_${PN}3 = "${WORKDIR}/script3.sh"
# PLATFORM_RUNTIME_TOOLS_STEP_${PN}3 = "core" 
# PLATFORM_RUNTIME_TOOLS_WHEN_${PN}3 = "common"
# PLATFORM_RUNTIME_TOOLS_PRIORITY_${PN}3 = "90"
# --------------------------------------------------------------
# This will end up with the following tree in /usr/libexec/platform-runtime-tools/customize/core/:
# .
# ├── arch
# │   └── x86_64
# │       └── 25_script1.sh             <= from id ${PN}1
# ├── board
# │   └── kingfisher-h3ulcb-r8a7795
# │       └── 45FB_script2.sh           <= from id ${PN}2
# ├── common
# │   └── 90_script3.sh                 <= from id ${PN}3
# └── vendor
#     └── renesas
#         |── 25_script1.sh             <= from id ${PN}1
#         └── 45FB_script2.sh           <= from id ${PN}2
#
# At runtime, what will happen ? platform-runtime-tools will run the scriptlets depending on the conditions:
# - if running on kingfisher with H3 board, the following scripts will be run:
#   script1.sh (matches vendor/renesas prio 25)
#   script2.sh (matches board/kingfisher-h3ulcb-r8a7795 prio 45)
#   script3.sh (matches common, prio 90)
# - if running on kingfisher with M3 board, the following scripts will be run:
#   script1.sh (matches vendor/renesas prio 25)
#   script3.sh (matches common, prio 90)
# - if running on minnowboard
#   script1.sh (matches arch/x86_64 prio 25)
#   script3.sh (matches common, prio 90)
#
# ##############################################################################################

# automatically add dependency
RDEPENDS_${PN}_prepend = "platform-runtime-tools "

# define basedir where scriptlets will be installed
PLATFORM_RUNTIME_TOOLS_BASEDIR = "${libexecdir}/platform-runtime-tools"

python do_platform_config_deploy() {
   import oe
   import shutil
   PREFIX="PLATFORM_RUNTIME_TOOLS"

   def classVar(d, var, id=None):
      if id==None:
         v=(d.getVar('%s_%s' % (PREFIX,var)) or "")
      else:
         v=(d.getVar('%s_%s_%s' % (PREFIX,var,id)) or "")
      return v

   def add_to_package(filename):
      var="FILES_"+d.getVar("PN")
      files=d.getVar(var,False) or ""
      if filename not in files.split():
         d.appendVar(var," "+filename)

   def install_scriptlets(id):
      scriptlets=classVar(d,"ADD",id)
      step=classVar(d,"STEP",id) or "core"
      when=classVar(d,"WHEN",id) or "common"
      prio=classVar(d,"PRIORITY",id) or 50
      prio=int(prio)
      if (prio<0):
         prio=0
      elif (prio>99):
         prio=99

      # check if to be run at firstboot only or not
      fbsuffix="FB" if classVar(d,"FIRSTBOOT",id) in ("y","yes","1","true") else ""

      for script in scriptlets.split():
         if not os.path.exists(script):
            bb.error("source file %s not found" % script)
            continue
         for cond in when.split():
            dst=oe.path.join(
               classVar(d,"BASEDIR"),
               step,
               cond,
               "%02d%s_%s" % (prio, fbsuffix, os.path.basename(script))
            )
            absdst=oe.path.join(
               d.getVar("D"),
               dst
            )
            bb.debug(1,"adding scriptlet %s to %s" % (script, absdst))
            bb.utils.mkdirhier(os.path.dirname(absdst))
            shutil.copy(script,absdst)
            os.chmod(oe.path.join(d.getVar("D"),classVar(d,"BASEDIR")),0o755)
            os.chmod(absdst,0o755)

            # add to FILES for current package
            add_to_package(dst)

   if os.path.exists(d.getVar("D")):
      for id in classVar(d,"IDS").split():
         install_scriptlets(id)
}

addtask platform_config_deploy before do_package after do_install

