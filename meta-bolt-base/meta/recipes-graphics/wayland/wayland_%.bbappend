
do_install:append() {

    # Remove the non-client parts of the wayland package
    rm -rf ${D}${libdir}/libwayland-cursor.so*
    rm -rf ${D}${datadir}/wayland
}

