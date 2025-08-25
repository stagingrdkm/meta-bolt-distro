include westeros.inc
  
SUMMARY = "Essos is a library making it simple to create applications that run either as native EGL or Wayland clients."
LICENSE = "Apache-2.0"
LICENSE_LOCATION = "${S}/../LICENSE"

SRC_URI:append = " file://001-enable-building-wayland-code-without-xkb.patch \
                 "

S = "${WORKDIR}/git/essos"

DEPENDS = "wayland wayland virtual/egl westeros-simpleshell"

inherit autotools pkgconfig

EXTRA_OECONF:append = " --enable-essoswesterosfree "
EXTRA_OECONF:append = " --enable-essosresmgrfree "

PACKAGES =+ "${PN}-examples"
FILES:${PN}-examples += "${bindir}/*"

