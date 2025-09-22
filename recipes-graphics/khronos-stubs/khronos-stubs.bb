SUMMARY          = "Stub libraries for EGL / OpenGL ES / Vulkan"
DESCRIPTION      = "Generates stub libraries from the official khronos header files. \
The libraries themselves do nothing, but allow code to link against them \
and therefore can be used for building native code. \
It is expected that at runtime these libraries are swapped out with vendor \
supplied versions. \
"

LICENSE          = "MIT & Apache-2.0"
HOMEPAGE         = "https://github.com/stagingrdkm/khronos-stubs"

SRC_URI          = "git://git@github.com/stagingrdkm/khronos-stubs.git;protocol=https;branch=main"
SRCREV           = "7be7474b1a457d017c7ad07e6076a79fb1afbaf3"

S                = "${WORKDIR}/git"

PROVIDES         = "virtual/egl virtual/libgles1 virtual/libgles2 virtual/libgles3"
RPROVIDES_${PN}  = "egl libgles1 libgles2 libgles3"

BBCLASSEXTEND    = "native"

inherit cmake pkgconfig


# skip dev-so in order for the unversioned so file to be packaged as well
FILES:${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:${PN} += "installed-vs-shipped"

