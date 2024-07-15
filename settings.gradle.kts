rootProject.name = "QRCodeDemo"
include(":composeApp")
include(":qrcodeScanner")
//include(":qrcodeGenerator")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
