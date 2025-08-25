DESCRIPTION = "Recipe to include the latest OCDM header files"
LICENSE = "CLOSED"

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

