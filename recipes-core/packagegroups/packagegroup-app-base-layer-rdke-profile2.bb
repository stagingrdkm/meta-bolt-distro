# packagegroup-app-base-layer-rdke-profile2.bb 
# Note we are not listing all packages for the moment like done 
# for done for RDK-E OSS layer https://github.com/rdkcentral/meta-rdk-oss-reference/blob/1.2.0/recipes-core/packagegroups/packagegroup-oss-layer.bb
# for done with RDK-E MW layer https://github.com/rdkcentral/meta-middleware-generic-support/blob/1.1.2-community/recipes-core/packagegroups/packagegroup-middleware-layer.bb
# is a TO DO for later but is not necessary for now because link dependent libraries are auto-added by yocto anyhow.
# Would only do after near final baselayer content agreement and only after doing practical analysis of libs in that base-layer-profile1
# possibly required before moving to prebuild base layer using release ipk in artifactory

SUMMARY = "App Base layer profile2 for RDKE aka Slim profile"

LICENSE = "MIT"

inherit packagegroup

PACKAGE_ARCH = "${MACHINE_ARCH}"
# TODO add common arch

PV = "0.1.0"
PR = "r0"

# Install glib-2.0 but without the mime info as this also pulls in libxml2
RDEPENDS:${PN} += " glib-2.0"
BAD_RECOMMENDATIONS:append = " shared-mime-info"

RDEPENDS:${PN} += "\
      libstdc++ \
      zlib \
"

RDEPENDS:${PN} += " dobby-init"

# Stuff required for basic graphics to display
RDEPENDS:${PN} += "\
     khronos-stubs \
     wayland \
     westeros-simpleshell \
     essos \
"
# Include the rialto libraries but not rialto-gstreamer
RDEPENDS:${PN} += "\
     rialto-client \
     rialto-ocdm \
"
# Current assumption is busybox will be part of Standard Profile of base-layer 
# Possibly later moved to debug base layer extension and use other shell provider like "dash" instead

RDEPENDS:${PN} += " busybox"

