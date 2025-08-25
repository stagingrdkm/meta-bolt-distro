
PACKAGECONFIG = ""

do_install:append() {

    # Remove the GIR files
    rm -rf ${D}${libdir}/girepository-1.0

}

