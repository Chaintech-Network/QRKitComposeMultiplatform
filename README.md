# QRKit - Compose Multiplatfrom
QRKit is a Kotlin Multiplatform library for Qr-Scan in your Android, iOS And Desktop App.

<img width="960" alt="QRKit_Compose Multiplatfrom_banner" src="https://github.com/Chaintech-Network/QRKitComposeMultiplatform/assets/143475887/6510ae51-6d79-4259-9654-61fb9d9e6cdb">

## Installation

Add the dependency to your `build.gradle.kts` file:

```kotlin
commonMain.dependencies {
    implementation("network.chaintech:qr-kit:1.0.7")
}
```

## Usage

### QrScanner

### Android
https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/assets/143475887/1e24f562-95aa-4452-9a19-30240bff13de

### iOS
https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/assets/143475887/60bfd192-3fbb-4509-940a-9ae44caaeeb2

### Desktop
https://github.com/Chaintech-Network/QRKitComposeMultiplatform/assets/143475887/a3966bb4-a28e-4a03-a0aa-a740445a8f6a


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
    openImagePicker: Boolean,
    onCompletion: (String) -> Unit,
    imagePickerHandler: (Boolean) -> Unit,
    onFailure: (String) -> Unit
)
```

* `modifier`: Modifier for modifying the layout of the QR scanner.
* `flashlightOn`: Boolean indicating whether the flashlight is turned on.
* `openImagePicker`: Boolean indicating whether to launch the picker for selecting images.
* `imagePickerHandler`: Callback invoked to indicate the status of the gallery, whether it's open or closed.
* `onFailure`: Callback invoked when there's a failure during QR code scanning.

### QrCode Generator

![QRKit_Compose Multiplatfrom_preview](https://github.com/Chaintech-Network/QRKitComposeMultiplatform/assets/143475887/8422f149-8621-4dc0-a0ac-d65cb54a40d1)

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


## Tech Stack(s)
* Compose Multiplatform
* CameraX Jetpack library
* ML Kit

For Image Picker we had used this [CMP Pick n Crop Library](https://github.com/Chaintech-Network/CMP-image-pick-n-crop)

- for more follow this class -> [App.kt](https://github.com/ChainTechNetwork/QRKitComposeMultiplatform/blob/main/composeApp/src/commonMain/kotlin/chaintech/qrkit/demo/App.kt)
- Want to understand in more detail? Checkout this [Medium Article](https://medium.com/mobile-innovation-network/qrkit-qrcode-generator-in-compose-multiplatform-for-android-and-ios-858ec3e1e2b2)
- Connect with us on [LinkedIn](https://www.linkedin.com/showcase/mobile-innovation-network)
