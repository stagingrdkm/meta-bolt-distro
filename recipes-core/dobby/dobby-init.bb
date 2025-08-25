SUMMARY = "Dobby Container Manager - Init Process"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://git/LICENSE;md5=c466d4ab8a68655eb1edf0bf8c1a8fb8"

include dobby.inc

SRC_URI:append = " file://CMakeLists.txt \
                 "

S = "${WORKDIR}"

inherit cmake


