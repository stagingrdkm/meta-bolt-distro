# meta-rdke-appsdk-base-dev
repo for App SDK Base Layer definition &amp; development, version tracking and base layer image generation in yocto.  
# how to setup and build
  #Pre-requisite : host build machine is setup for yocto kirkstone building and has google repo tool installed, see  
  #"Build host deps for yocto https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html#build-host-packages" and  
  #"install repo tool on build host https://android.googlesource.com/tools/repo "

  #Create a build directory to work in, once per "abuild" setup dir  
  mkdir abuild; cd abuild

  #initialise and code sync with repo tool the manifest with repositories for this App SDK base layer project  
  #repo init is once per "abuild" dir setup  
  repo init -u https://github.com/stagingrdkm/meta-rdke-appsdk-base-dev/ -m manifests/manifest-base.xml  
  repo sync --no-clone-bundle -v -j$(getconf _NPROCESSORS_ONLN)

  #yocto poky build environment setup script. Once per shell you are building in  
  source oe-init-build-env

  #configure your conf/local.conf and set DISTRO to "rdke-appsdk" and MACHINE config to one of supported machines   
  #there is template you can copy from  
  cp ../meta-rdke-appsdk-base-dev/conf/templates/local.conf.sample conf/local.conf  
  cp -r ../meta-rdke-appsdk-base-dev/conf/templates/mu* conf/.  
  vi conf/local.conf  
  #doublecheck $grep DISTRO conf/local.conf  Need to see DISTRO = "rdke-appsdk"  

  #The App SDK supports 3 ABI's  
  #You need to configure associated machine in conf/local.conf of your build/ dir. for example:  
  vi conf/local.conf  
  MACHINE = "arm"  
  #You need to select one of the following machine configurations:  
  #1."arm"   - arm 32bit mode, APP SDK is only for compiling Apps in userland, kernel can be running 32bit or 64bit mixed mode  
  #2."arm64" - arm 64bit mode (requires 64bit kernel, RDK is not fully supporting RDK Middleware in full 64bit mode yet)  
  #3."amd64" - x86 64bit mode, for running of x86 PC/virtual device  
  
  #coppy prepared bblayers.conf file from meta-rdke-appsdk-base/manifest repo to local bblayers.conf.  
  #Once per "abuild" setup  
  cp ../meta-rdke-appsdk-base-dev/conf/templates/bblayers.conf.sample conf/bblayers.conf  

  #build the base image, rdk-app-base-image-1 stands for profile 1 of base image.  
  bitbake rdke-app-baselayer-p1  
  #if you want to compile for all CPU_arch in one go, output will be 3 separate images in deploy dir   
  bitbake rdke-app-baselayer-p1:multiconfig:arm32 rdke-app-baselayer-p1:multiconfig:arm64 rdke-app-baselayer-p1:multiconfig:amd64  


