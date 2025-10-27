# manifests
There are application, runtime and base layer artifacts that can be build with this Firebolt Yocto based meta-bolt-distro.

To build specific artifact you need to repo init the associated manifest file within this manifest dir structure using the repo tool.
That will fetch the git repositories outlined in the manifest and required to build your artifact.
Repo tool can be fetched from https://gerrit.googlesource.com/git-repo 

The directory structure within manifests dir is further divided based on type of artifact or sdk-util-manifests
app/
app-restricted/
base/
runtime/
sdk-oss/
sdk-tools/

manifests/app/ contains manifests for apps. This is further devided per company/entity responsible for maintaining associated app-manifests.

manifests/runtime/{runtimename}/rel will contain official release versions of "RDK runtimes" maintained and released by RDK-M team. 
   It also allows and encourgages other companies to share their version or updates of existing RDK runtimes or share new runtimes.
   Allowing exact build replication and auto-publishing in Appstore for Video Accelerators

manifests/base/rel/ will contain official release versions of "base-layer" source manifest. Version and content is controlled by RDK Native App Working Group.  
  The AppSDK will come with prebuild profiles of this base-layer version and SDK toolchain export in /sdk-export.  
  This versioned manifest allows exact replication of that binary version from sources with the AppSDK
  The base.dev.xml is the "next" version that is still under development, not garanteed to be fixed versioned. 

There is manifest file name and directory convention that is described in manifest-naming-convention.txt file.

Note that to run these artifacts a RDK-8 device or emulator is required

