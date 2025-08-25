
# Remove the libxml2 dependency, it is not needed for the core gstreamer libs
DEPENDS:remove = "libxml2"

# Disable the tools
PACKAGECONFIG:remove:pn-${PN} = "tools"

do_install:append() {

    # Remove the GIR files
    rm -rf ${D}${libdir}/girepository-1.0

}

