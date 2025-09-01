# meta-rdk-app-sdk-base-dev
repo for App SDK Base Layer definition &amp; development, version tracking and base layer image generation.

  # how to setup and build
  Pre-requisite : host build machine is setup for yocto kirkstone building and has google repo tool installed, see  
  "Build host deps for yocto https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html#build-host-packages" and  
  "install repo tool on build host https://android.googlesource.com/tools/repo "

  #Create a build directory to work in, once per "abuild" setup dir  
  mkdir abuild; cd abuild

  #initialise and code sync with repo tool the manifest with repositories for this App SDK base layer project  
  #repo init is once per "abuild" dir setup  
  repo init -u https://github.com/stagingrdkm/meta-rdk-app-sdk-base-dev/ -m manifests/manifest-base.xml  
  repo sync --no-clone-bundle -v -j$(getconf _NPROCESSORS_ONLN)

  #yocto poky build environment setup script. Once per shell you are building in  
  source oe-init-build-env

  #set in local.conf DISTRO to "rdk-app-base-layer" and MACHINE config to one of supported machines  
  vi conf/local.conf  
  #set  [ DISTRO ?= "rdk-app-base-layer" ] [ MACHINE = "rdk-arm" ]. Once per "abuild" setup  

  #doublecheck $grep DISTRO conf/local.conf  Need to see DISTRO ?= "rdk-app-base-layer"  

  #coppy prepared bblayers.conf file from meta-rdk-app-sdk-base/manifest repo to local bblayers.conf. Once per "abuild" setup  
  cp ../.repo/manifests/manifests/bblayers.conf conf/  

  #build the base image, rdk-app-base-image-1 stands for profile 1 of base image.  
  bitbake rdke-app-baselayer-p1-oci
  


  



