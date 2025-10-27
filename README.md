# Firebolt meta-bolt-distro
This repository defines and provides the **RDK Firebolt Yocto distribution**, derived from Yocto **Poky** and tailored to build hardware-agnostic **Firebolt Native Applications** and **runtimes**.

A Firebolt Native Application is referred to as a **"bolt" app** or simply a **"bolt"**. Each bolt represents an application or runtime layer (in a defined OCI artifact format) that, together with the Firebolt mandatory **commom base layer**, forms a second-generation Downloadable Application Container (**DAC2**) within RDK. The base layer is defined in this distribution (see **meta-bolt-base**).

To support bolt application development in other build and development environments than Yocto, each release of this distribution also has an associated Yocto SDK export, available on the firebolt-sdk repository. The **SDK** release then includes the **cross-compilation toolchain** defined by this distribution (supporting arm, arm64, and amd64 targets), along with **binary releases of the base layer**, header files, and other tools required to build bolt applications.

A **release version** of this distribution, together with its associated base-layer version, provides **support for one or more specific major RDK OS versions** (e.g., **RDK-8**).  
Bolt applications intended to run across devices within a particular major RDK OS version must be built using the corresponding **compatible release** of this distribution and its base layer.

Each release version of this distribution is derived from a specific release version the Yocto Poky reference distribution.

**This repository includes**:

- bitbake config files defining the "bolt" distro
- meta-bolt-base - meta-layer with recipes providing configuration for packages used by the base layer
- meta-bolt-app-examples - meta-layer with recipes required for building sample applications. This layer is not enabled by default; to use it, it must be added to the bblayers.conf file
- meta-bolt-tools - meta-layer with recipes for tools related to creation of OCI artifacts from build artifacts and interaction with STB
- manifests - repo tool manifest files defining all meta-layer repositories and versions required for specific version of this distribution and to build specific version of base layer or example application layer oci artifacts using this distribution.

# how to setup and build base-layer
  #Pre-requisite : host build machine is setup for yocto kirkstone building and has google repo tool installed, see  
  #"Build host deps for yocto https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html#build-host-packages" and  
  #"install repo tool on build host https://android.googlesource.com/tools/repo "

  #Create a build directory to work in, once per "abuild" setup dir  
  mkdir abuild; cd abuild

  #initialise and code sync with repo tool the manifest with repositories for this App SDK base layer project  
  #repo init is once per "abuild" dir setup  
  #old repo init -u https://github.com/stagingrdkm/meta-bolt-distro/ -m manifests/manifest-base.xml  
  repo init -u https://github.com/rdkcentral/meta-bolt-distro/ -m manifests/base/base.dev.xml   
  repo sync --no-clone-bundle -v -j$(getconf _NPROCESSORS_ONLN)

  #yocto poky build environment setup script. Once per shell you are building in  
  source oe-init-build-env

  #configure your conf/local.conf and bblayers.conf once per "abuild" setup  
  #there is template you can copy from  
  cp ../bolt/meta-bolt-base/conf/templates/local.conf.sample conf/local.conf  
  cp ../bolt/meta-bolt-base/conf/templates/bblayers.conf.sample conf/bblayers.conf  
  #copy MULTICONFIG template to allow compilation for all machines in one command  
  cp -r ../bolt/meta-bolt-base/conf/templates/mu* conf/.    
  vi conf/local.conf  
  #doublecheck DISTRO = "rdke-appsdk"  
  grep DISTRO conf/local.conf  
  #set your local differences like DL_DIR  
  vi conf/local.conf  

  #The App SDK supports 3 ABI's  
  #You need to select one of the following machine configurations in conf/local.conf:  
  #1."arm"   - arm 32bit mode, SDK is only for compiling Apps in userland, kernel can be running 32bit or 64bit mixed mode  
  #2."arm64" - arm 64bit mode, requires 64bit kernel, RDK middleware is not fully supporting 64bit mode yet  
  #3."amd64" - x86 64bit mode, for running of x86 PC/virtual device  
  #default config in local.conf.sample is arm. You can adapt with  
  vi conf/local.conf  
  MACHINE = "arm" 
 
  #build the base image, rdk-app-base-image-1 stands for profile 1 of base image.  
  bitbake rdke-app-baselayer-p1  
  #if you want to compile for all CPU_arch in one go, output will be 3 separate images in deploy dir   
  bitbake multiconfig:arm:rdke-app-baselayer-p1 \  
         multiconfig:arm64:rdke-app-baselayer-p1 \   
         multiconfig:amd64:rdke-app-baselayer-p1  


