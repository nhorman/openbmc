SUMMARY = "Phosphor OpenBMC SSIF to DBUS"
DESCRIPTION = "Phosphor OpenBMC SSIF to DBUS."
PR = "r1"
PV = "1.0+git${SRCPV}"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit meson pkgconfig
inherit systemd

SYSTEMD_SERVICE:${PN} = "ssifbridge.service"

PROVIDES += "virtual/obmc-host-ipmi-hw"
RPROVIDES:${PN} += "virtual-obmc-host-ipmi-hw"
RRECOMMENDS:${PN} += "phosphor-ipmi-host"

DEPENDS += "systemd"
DEPENDS += "phosphor-logging"
DEPENDS += "sdbusplus"
DEPENDS += "cli11"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/openbmc/ssifbridge.git;protocol=https;branch=master"
SRCREV= "88cdf7d12a0e4a8377e5ec664bd60a456cbbb5f3"

