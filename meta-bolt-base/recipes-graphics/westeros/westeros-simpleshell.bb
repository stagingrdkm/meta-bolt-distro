include westeros.inc

SUMMARY = "This receipe compiles the westeros compositor simple-shell component"

LICENSE = "Apache-2.0"

SRC_URI:append = " file://001-disable-glib-gthread-requirement.patch;patchdir=../ \
                   file://002-only-build-client-lib.patch;patchdir=../ \
                   file://westeros-simpleshell-client.pc \
                 "

S = "${WORKDIR}/git/simpleshell"

DEPENDS = "wayland wayland-native"

LICENSE_LOCATION = "${S}/../LICENSE"

inherit autotools pkgconfig

do_compile:prepend() {
    export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
    oe_runmake -C ${S}/protocol
}

do_install:append() {
    install -d ${D}${libdir}/pkgconfig
    install -m 0644 ${WORKDIR}/westeros-simpleshell-client.pc ${D}${libdir}/pkgconfig/
}

