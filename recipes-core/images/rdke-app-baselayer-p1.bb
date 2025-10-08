SUMMARY = "RDKE base layer image for apps, profile1"
LICENSE = "MIT"

IMAGE_FSTYPES = "container oci"

inherit image
inherit image-oci

# TODO P3 clarify with legal if we cannot set this INCOMPATIBLE_LICESE here at image level as recommend by yocto community iso setting it global at distro level
# currently set at distro level because legal safest but has tech drawbacks
#INCOMPATIBLE_LICENSE = "LGPL-3.0-or-later LGPL-3.0-only GPL-3.0-or-later GPL-3.0-only"

# uncomment to generate from libs in the rootfs the .so depedency tree and offered API version
# output is $MACHINE_libs.json in DEPLOY_DIR_IMAGE
#inherit generate_libs_json

# TODO: Do we need different locale info in the base image ?
IMAGE_LINGUAS = " "

# installing baselayer rdke profile1, it's content is defined in this packagegroup
IMAGE_INSTALL:append = " packagegroup-app-base-layer-rdke-profile1"

# Create a readonly rootfs
IMAGE_FEATURES += "read-only-rootfs"

NO_RECOMMENDATIONS = "1"

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
