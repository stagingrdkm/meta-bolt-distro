DESCRIPTION = "Recipe to include the latest OCDM header files"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=70514b59ff7b36bbbc30d093c6814d8e"

SRC_URI = "file://open_cdm.h \
           file://open_cdm_ext.h \
           file://open_cdm_adapter.h \
           file://LICENSE \  
          "

S = "${WORKDIR}"

do_install() {
    install -d ${D}${includedir}/opencdm
    install -m 0644 ${WORKDIR}/open_cdm.h ${D}${includedir}/opencdm/
    install -m 0644 ${WORKDIR}/open_cdm_ext.h ${D}${includedir}/opencdm/
    install -m 0644 ${WORKDIR}/open_cdm_adapter.h ${D}${includedir}/opencdm/
}

