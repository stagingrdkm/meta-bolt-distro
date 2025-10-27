SUMMARY          = "Stub libraries for EGL / OpenGL ES / Vulkan"
DESCRIPTION      = "Generates stub libraries from the official khronos header files. \
The libraries themselves do nothing, but allow code to link against them \
and therefore can be used for building native code. \
It is expected that at runtime these libraries are swapped out with vendor \
supplied versions. \
"

LICENSE          = "MIT & Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e7a4bfe3b44b069785947f263062d29f"
HOMEPAGE         = "https://github.com/stagingrdkm/khronos-stubs"

SRC_URI          = "git://git@github.com/stagingrdkm/khronos-stubs.git;protocol=https;branch=main"
SRCREV           = "fcea14e15bf6062082fa4cc6580f197db5be61eb"

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

