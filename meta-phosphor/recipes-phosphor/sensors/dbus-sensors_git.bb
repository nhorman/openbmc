SUMMARY = "dbus-sensors"
DESCRIPTION = "Dbus Sensor Services Configured from D-Bus"

SRC_URI = "git://github.com/openbmc/dbus-sensors.git;branch=master;protocol=https"
SRCREV = "6c106d66e390352a6914e8dc5ddad20943eceaa6"

PV = "0.1+git${SRCPV}"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PACKAGECONFIG ??= " \
    adcsensor \
    intelcpusensor \
    exitairtempsensor \
    fansensor \
    hwmontempsensor \
    intrusionsensor \
    ipmbsensor \
    mcutempsensor \
    psusensor \
    external \
    "

PACKAGECONFIG[adcsensor] = "-Dadc=enabled, -Dadc=disabled"
PACKAGECONFIG[intelcpusensor] = "-Dintel-cpu=enabled, -Dintel-cpu=disabled"
PACKAGECONFIG[exitairtempsensor] = "-Dexit-air=enabled, -Dexit-air=disabled"
PACKAGECONFIG[fansensor] = "-Dfan=enabled, -Dfan=disabled"
PACKAGECONFIG[hwmontempsensor] = "-Dhwmon-temp=enabled, -Dhwmon-temp=disabled"
PACKAGECONFIG[intrusionsensor] = "-Dintrusion=enabled, -Dintrusion=disabled"
PACKAGECONFIG[ipmbsensor] = "-Dipmb=enabled, -Dipmb=disabled"
PACKAGECONFIG[mcutempsensor] = "-Dmcu=enabled, -Dmcu=disabled"
PACKAGECONFIG[psusensor] = "-Dpsu=enabled, -Dpsu=disabled"
PACKAGECONFIG[nvmesensor] = "-Dnvme=enabled, -Dnvme=disabled"
PACKAGECONFIG[external] = "-Dexternal=enabled, -Dexternal=disabled"

SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'adcsensor', \
                                               'xyz.openbmc_project.adcsensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'intelcpusensor', \
                                               'xyz.openbmc_project.intelcpusensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'exitairtempsensor', \
                                               'xyz.openbmc_project.exitairsensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'fansensor', \
                                               'xyz.openbmc_project.fansensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'hwmontempsensor', \
                                               'xyz.openbmc_project.hwmontempsensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'intrusionsensor', \
                                               'xyz.openbmc_project.intrusionsensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'ipmbsensor', \
                                               'xyz.openbmc_project.ipmbsensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'mcutempsensor', \
                                               'xyz.openbmc_project.mcutempsensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'psusensor', \
                                               'xyz.openbmc_project.psusensor.service', \
                                               '', d)}"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'external', \
                                               'xyz.openbmc_project.externalsensor.service', \
                                               '', d)}"

DEPENDS = " \
    boost \
    i2c-tools \
    libgpiod \
    liburing \
    nlohmann-json \
    phosphor-logging \
    sdbusplus \
    "
inherit pkgconfig meson systemd

S = "${WORKDIR}/git"

EXTRA_OEMESON:append = " -Dtests=disabled"
