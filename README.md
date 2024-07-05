# QRKit - Compose Multiplatfrom
QRKit is a Kotlin Multiplatform library for Qr-Scan in your Android or iOS App.

![Hero-image - QR generator  amp;amp; scanner 2](https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/assets/143475887/b270c630-c4a4-49be-be53-d0b9693c0a80)

## Installation

Add the dependency to your `build.gradle.kts` file:

```kotlin
commonMain.dependencies {
    implementation("network.chaintech:qr-kit:1.0.6")
}
```

## Usage

### QrScanner

### Android
https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/assets/143475887/1e24f562-95aa-4452-9a19-30240bff13de



### iOS
https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/assets/143475887/60bfd192-3fbb-4509-940a-9ae44caaeeb2



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
* `openImagePicker`: Boolean indicating whether to launch the picker for selecting images.
* `onCompletion`: Callback invoked when a QR code is successfully scanned.
* `onGalleryCallBackHandler`: Callback invoked to indicate the status of the gallery, whether it's open or closed.
* `onFailure`: Callback invoked when there's a failure during QR code scanning.

### QrCode Generator

![image](https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/assets/143475887/470f6547-5121-4c19-ab51-74c14bf284ba)

### Example

```kotlin
QRCodeImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
    onSuccess: (ImageBitmap) -> Unit = { qrImage -> },
    onFailure: (String) -> Unit = { message -> }
)
```

- And also use below function to generate qrcode

```kotlin
fun generateQrCode(
    url: String,
    onSuccess: (String, ImageBitmap) -> Unit,
    onFailure: (String) -> Unit
) {
    try {
        val imageBitmap = generateCode(url)
        onSuccess(url, imageBitmap)
    } catch (e: Exception) {
        onFailure("${e.message}")
    }
}
```

* `url`: The URL of the QR code image to be displayed.
* `contentDescription`: A textual description of the image content for accessibility purposes. It's optional.
* `modifier`: Modifier for modifying the layout of the QR code image.
* `alignment`: Alignment of the QR code image within its layout bounds. Default is Alignment.Center.
* `contentScale`: The scale strategy for fitting the QR code image content within its layout bounds. Default is ContentScale.Fit.
* `alpha`: The opacity of the QR code image, ranging from 0.0 (fully transparent) to 1.0 (fully opaque). Default is DefaultAlpha.
* `colorFilter`: A color filter to apply to the QR code image. Default is null.
* `filterQuality`: The quality of the filtering applied to the QR code image. Default is DrawScope.DefaultFilterQuality.
* `onSuccess`: Callback invoked when the QR code image is successfully loaded and decoded, passing the decoded ImageBitmap as a parameter.
* `onFailure`: Callback invoked when there's a failure during loading or decoding the QR code image, passing an error message as a parameter.


## Tech Stack
* Compose Multiplatform
* CameraX Jetpack library
* ML Kit

For Image Picker we had used this [CMP Pick n Crop Library](https://github.com/Chaintech-Network/CMP-image-pick-n-crop)

- for more follow this class -> [App.kt](https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/blob/main/composeApp/src/commonMain/kotlin/chaintech/qrkit/demo/App.kt)
