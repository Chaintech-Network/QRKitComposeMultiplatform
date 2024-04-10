# QRKit - Compose Multiplatfrom
QRKit is a Kotlin Multiplatform library for Qr-Scan in your Android or iOS App.


## Installation

Add the dependency to your `build.gradle.kts` file:

```kotlin
commonMain.dependencies {
    implementation("network.chaintech:kmp-date-time-picker:1.0.2")
}
```

## Usage

### QrScanner

### Android
https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/assets/143475887/1e24f562-95aa-4452-9a19-30240bff13de



### iOS


### Add Permissions in Android and iOS

- Android : Include this at root level in your AndroidManifest.xml:

```
<uses-feature android:name="android.hardware.camera"/>
<uses-feature android:name="android.hardware.camera.autofocus"/>
<uses-permission android:name="android.permission.CAMERA"/>
<uses-permission android:name="android.permission.FLASHLIGHT"/>
```

- iOS : Add below key to the Info.plist in your xcode project:

```
<key>NSCameraUsageDescription</key><string>$(PRODUCT_NAME) camera description.</string>
<key>NSPhotoLibraryUsageDescription</key><string>$(PRODUCT_NAME)photos description.</string>
```

### Example

```kotlin
QrScanner(
    modifier: Modifier,
    flashlightOn: Boolean,
    launchGallery: Boolean,
    onCompletion: (String) -> Unit,
    onGalleryCallBackHandler: (Boolean) -> Unit,
    onFailure: (String) -> Unit
)
```

* `modifier`: Modifier for modifying the layout of the QR scanner.
* `flashlightOn`: Boolean indicating whether the flashlight is turned on.
* `launchGallery`: Boolean indicating whether to launch the gallery for selecting images.
* `onCompletion`: Callback invoked when a QR code is successfully scanned.
* `onGalleryCallBackHandler`: Callback invoked to indicate the status of the gallery, whether it's open or closed.
* `onFailure`: Callback invoked when there's a failure during QR code scanning.

## Tech Stack
* Compose Multiplatform
* CameraX Jetpack library
* ML Kit

- for more follow this class -> [App.kt](www.google.com)
