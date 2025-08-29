SUMMARY = "RDK base layer image for apps, profile1"

IMAGE_FSTYPES = "container oci"

inherit image
inherit image-oci


# TODO: Do we need different locale info in the base image ?
IMAGE_LINGUAS = " "


IMAGE_INSTALL:append = " libstdc++"
IMAGE_INSTALL:append = " zlib"

IMAGE_INSTALL:append = " dobby-init"

# Stuff required for basic graphics to display
IMAGE_INSTALL:append = " khronos-stubs"
IMAGE_INSTALL:append = " wayland"
IMAGE_INSTALL:append = " westeros-simpleshell"
IMAGE_INSTALL:append = " essos"

# Install glib-2.0 but without the mime info as this also pulls in libxml2
IMAGE_INSTALL:append = " glib-2.0"
BAD_RECOMMENDATIONS:append = " shared-mime-info"

# Install just the gstreamer1.0 libs, not any plugins
IMAGE_INSTALL:append = " gstreamer1.0"
IMAGE_INSTALL:append = " gstreamer1.0-plugins-base"

# Install the rialto libraries
IMAGE_INSTALL:append = " rialto-client"
IMAGE_INSTALL:append = " rialto-ocdm"
IMAGE_INSTALL:append = " rialto-gstreamer"

# Current assumption is busybox will be part of base-layer. 
#Possibly later moved to debug base layer extension and use other shell provider like "dash" package as in meta-dac-sdk 
# adding busybox explicitly for now. Alternative might be in distro config PREFERRED_PROVIDER_virtual/sh = "busybox" or PREFERRED_PROVIDER_virtual/base-utils = "busybox"
IMAGE_INSTALL:append = " busybox"

# Create a readonly rootfs
IMAGE_FEATURES += "read-only-rootfs"

LICENSE = "MIT"

TOOLCHAIN_TARGET_TASK:append = " libstdc++-dev"
TOOLCHAIN_TARGET_TASK:append = " dobby-init-dev"


ROOTFS_POSTPROCESS_COMMAND:append = " stub_gpu_libraries;"
ROOTFS_POSTPROCESS_COMMAND:append = " add_ca_certificate_mount_points;"


# This removes the stub and actual GPU libraries from the rootfs and replaces
# then with regular files as mount points.  At runtime the real versions of
# these libraries will be bind mounted in from the vendor rootfs.
stub_gpu_libraries() {
    rm -f ${IMAGE_ROOTFS}/usr/lib/libwayland-egl.so*
    touch ${IMAGE_ROOTFS}/usr/lib/libwayland-egl.so.1

    rm -f ${IMAGE_ROOTFS}/usr/lib/libEGL.so.1
    touch ${IMAGE_ROOTFS}/usr/lib/libEGL.so.1

    rm -f ${IMAGE_ROOTFS}/usr/lib/libGLESv1_CM.so.1
    touch ${IMAGE_ROOTFS}/usr/lib/libGLESv1_CM.so.1

    rm -f ${IMAGE_ROOTFS}/usr/lib/libGLESv2.so.2
    touch ${IMAGE_ROOTFS}/usr/lib/libGLESv2.so.2
}


# Adds some standard mount points for the SSL CA certs from the system, this
# is very EntOS specific and probably want to bundle a set of CA certs into
# the base layer - but this may be controversial
add_ca_certificate_mount_points() {
   install -d ${IMAGE_ROOTFS}/etc/ssl/certs
   install -d ${IMAGE_ROOTFS}/usr/share/ca-certificates
}

# Is this required? Point first exec in oci image. need to test/check if needed for now point to empty or DobbyInit
OCI_IMAGE_ENTRYPOINT = "/usr/libexec/DobbyInit"

OCI_IMAGE_TAR_OUTPUT=""

IMAGE_CMD:oci:append() {

    if [ -n "$image_name" ]; then
        file_name="$image_name.tar"
    else
        image_name="${IMAGE_NAME}${IMAGE_NAME_SUFFIX}-oci"
        file_name="${IMAGE_NAME}${IMAGE_NAME_SUFFIX}-oci-${OCI_IMAGE_TAG}-${OCI_IMAGE_ARCH}${OCI_IMAGE_SUBARCH:+"-$OCI_IMAGE_SUBARCH"}-linux.oci-image.tar"
    fi

    if [ -z "${OCI_IMAGE_TAR_OUTPUT}" ]; then
        tar --sort=name --format=posix --numeric-owner -cf ${file_name} -C ${image_name} .
    fi

    ln -fs ${file_name} ${IMAGE_BASENAME}.tar
}
