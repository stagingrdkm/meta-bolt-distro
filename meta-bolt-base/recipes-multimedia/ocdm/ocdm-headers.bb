DESCRIPTION = "Recipe to include the latest OCDM header files"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://open_cdm.h \
           file://open_cdm_ext.h \
           file://open_cdm_adapter.h \
          "

S = "${WORKDIR}"

do_install() {
    install -d ${D}${includedir}/opencdm
    install -m 0644 ${WORKDIR}/open_cdm.h ${D}${includedir}/opencdm/
    install -m 0644 ${WORKDIR}/open_cdm_ext.h ${D}${includedir}/opencdm/
    install -m 0644 ${WORKDIR}/open_cdm_adapter.h ${D}${includedir}/opencdm/
}

