# packagegroup-app-base-layer-rdke-debug-extension.bb
# defines packages added in debug version, usefull for debugging. Will not be added in release and production version

SUMMARY = "App Base layer debug extension packages for RDKE profiles"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"
# TODO add common arch

inherit packagegroup

PV = "0.1.0"
PR = "r0"

#EMPTY for now, TO DO

