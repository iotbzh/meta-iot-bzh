do_configure_append_porter() {
   kernel_configure_variable BT_HCIBCM203X m
   kernel_configure_variable BT_HCIBPA10X m
   kernel_configure_variable BT_HCIBFUSB m

   kernel_configure_variable WIRELESS_EXT y
   kernel_configure_variable WEXT_CORE y
   kernel_configure_variable WEXT_PROC y
   kernel_configure_variable WEXT_PRIV y
   kernel_configure_variable CFG80211 m
   kernel_configure_variable LIB80211 y
   kernel_configure_variable NL80211_TESTMODE y
   kernel_configure_variable CFG80211_DEFAULT_PS y
   kernel_configure_variable CFG80211_INTERNAL_REGDB y
   kernel_configure_variable MAC80211 m
   kernel_configure_variable MAC80211_HAS_RC y
   kernel_configure_variable MAC80211_RC_PID y
   kernel_configure_variable MAC80211_RC_MINSTREL y
   kernel_configure_variable MAC80211_RC_MINSTREL_HT y
   kernel_configure_variable MAC80211_RC_DEFAULT_MINSTREL y
   kernel_configure_variable MAC80211_RC_DEFAULT "minstrel_ht"
   kernel_configure_variable MAC80211_LEDS y
   kernel_configure_variable RFKILL_LEDS y
   kernel_configure_variable EEPROM_93CX6 m

   kernel_configure_variable USB_USBNET m
   kernel_configure_variable USB_NET_AX8817X m
   kernel_configure_variable USB_NET_AX88179_178A m
   kernel_configure_variable USB_NET_CDCETHER m
   kernel_configure_variable USB_NET_CDC_NCM m
   kernel_configure_variable USB_NET_NET1080 m
   kernel_configure_variable USB_NET_RNDIS_HOST m
   kernel_configure_variable USB_NET_CDC_SUBSET m
   kernel_configure_variable USB_BELKIN y
   kernel_configure_variable USB_ARMLINUX y
   kernel_configure_variable USB_NET_ZAURUS m
   kernel_configure_variable AT76C50X_USB m
   kernel_configure_variable USB_ZD1201 m
   kernel_configure_variable USB_NET_RNDIS_WLAN m
   kernel_configure_variable RTL8187 m
   kernel_configure_variable RTL8187_LEDS y
   kernel_configure_variable RT2X00 m
   kernel_configure_variable RT2500USB m
   kernel_configure_variable RT73USB m
   kernel_configure_variable RT2800USB m
   kernel_configure_variable RT2800USB_RT33XX y
   kernel_configure_variable RT2800USB_RT35XX y
   kernel_configure_variable RT2800_LIB m
   kernel_configure_variable RT2X00_LIB_USB m
   kernel_configure_variable RT2X00_LIB m
   kernel_configure_variable RT2X00_LIB_FIRMWARE y
   kernel_configure_variable RT2X00_LIB_CRYPTO y
   kernel_configure_variable RT2X00_LIB_LEDS y
   kernel_configure_variable RTLWIFI m
   kernel_configure_variable RTLWIFI_DEBUG y
   kernel_configure_variable RTL8192CU m
   kernel_configure_variable RTL8192C_COMMON m
   kernel_configure_variable ZD1211RW m
   kernel_configure_variable USB_WDM m

   kernel_configure_variable LEDS_TRIGGERS y

   kernel_configure_variable GENERIC_PHY y
   kernel_configure_variable PHY_RCAR_GEN2 m

   kernel_configure_variable CRYPTO_ARC4 m
   kernel_configure_variable CRC_CCITT m
   kernel_configure_variable CRC_ITU_T m
   kernel_configure_variable AVERAGE y

   # initrd support + NBD support
   kernel_configure_variable BLK_DEV_INITRD y
   kernel_configure_variable INITRAMFS_SOURCE ""
   kernel_configure_variable RD_GZIP y
   kernel_configure_variable BLK_DEV_NBD y
   kernel_configure_variable BLK_DEV_RAM y
   kernel_configure_variable BLK_DEV_RAM_COUNT 16
   kernel_configure_variable BLK_DEV_RAM_SIZE 4096
   kernel_configure_variable BLK_DEV_XIP y
   kernel_configure_variable DECOMPRESS_GZIP y

   yes '' | oe_runmake -C ${S} O=${B} oldconfig
}

# linux-firmware is needed for some drivers
RDEPENDS_kernel-modules += "linux-firmware"
