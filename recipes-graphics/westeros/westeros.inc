LICENSE = "Apache-2.0"
  
PV = "1.0+git"

SRC_URI = "${WESTEROS_URI}"
SRCREV = "${WESTEROS_SRCREV}"

WESTEROS_URI = "git://code.rdkcentral.com/r/components/opensource/westeros;protocol=https;branch=master;name=westeros"

# WESTEROS_URI ?= "${CMF_GIT_ROOT}/components/opensource/westeros;protocol=${CMF_GIT_PROTOCOL};branch=${CMF_GIT_MASTER_BRANCH};name=westeros"
# Tip of westeros master as of Jun 24, 2025 Westeros 1.01.59
WESTEROS_SRCREV = "9fa8be182c9bda04de9633b04b566047f2e9677a"

LICENSE_LOCATION ?= "${S}/LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=8fb65319802b0c15fc9e0835350ffa02"

SRCREV_FORMAT = "westeros"

