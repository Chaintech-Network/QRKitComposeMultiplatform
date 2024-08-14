# QRKit - Compose Multiplatfrom
QRKit is a Compose Multiplatform library designed for seamless QR code scanning & QR code Generating across Android, iOS, and Desktop applications.

<img width="960" alt="QR-code-generator-scanner" src="https://github.com/user-attachments/assets/34bdbfde-7ed1-4d6d-924b-b3af4f6b01c5">

## Installation

Add the dependency to your `build.gradle.kts` file:

```kotlin
commonMain.dependencies {
    implementation("network.chaintech:qr-kit:2.0.0")
}
```

## QrScanner

![QRCodeScannerCMP](https://github.com/user-attachments/assets/6d29f565-4678-445f-9609-e7a299a8945c)

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

### Usage

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

For Example follow [QrScannerScreen.kt](https://github.com/Chaintech-Network/QRKitComposeMultiplatform_org/blob/qrkit/composeApp/src/commonMain/kotlin/org/qrcodedemo/app/ui/QrScannerScreen.kt)

### Tech Stack(s) for scanner
* Compose Multiplatform
* CameraX Jetpack library
* ML Kit
  
---

## QrCode Generator

<img width="960" alt="QRCodeGenerator" src="https://github.com/user-attachments/assets/9569c123-8dfb-4897-9b50-5bbdd91ed3fb">

<img width="960" alt="QRCodeCustomization" src="https://github.com/user-attachments/assets/eb47dc54-644c-496e-a78d-f52c1a23df43">

### Basic QR Code Generation
Generating a basic QR code is as simple as using the rememberQrKitPainter function:

```kotlin
val painter = rememberQrKitPainter(data = inputText)

Image(
    painter = painter, 
    contentDescription = null, 
    modifier = Modifier.size(100.dp)
)
```

### Designed QR Code Generation
To create a more customized QR code, you can use rememberQrKitPainter function with additional options:

```kotlin
val centerLogo = painterResource(Res.drawable.ic_youtube)

val painter = rememberQrKitPainter(
    data = inputText,
    options = {
        centerLogo { painter = centerLogo }

        qrColors {
            darkColorBrush = QrKitBrush.customBrush {
                Brush.linearGradient(
                    0f to Color.White,
                    1f to Color.White,
                    end = Offset(it, it)
                )
            }
            frameColorBrush = QrKitBrush.solidBrush(Color.Blue)
        }

        qrShapes {
            ballShape = getSelectedQrBall(QrBallType.SquareQrBall())
            darkPixelShape = getSelectedPixel(PixelType.SquarePixel())
            frameShape = getSelectedQrFrame(QrFrameType.SquareQrFrame())
            qrCodePattern = getSelectedPattern(PatternType.SquarePattern)
        }
    }
)
```

### Explanation

#### 1. Creating a QR Code Painter
To create a QR code painter, use the rememberQrKitPainter function and pass in the input text:

```
val painter = rememberQrKitPainter(inputText)
```
This will create a QR code painter with the specified input text.

#### 2. Setting the Center Logo
To set the center logo of the QR code, use the centerLogo function:

```
centerLogo { painter = centerLogo }
```
This will set the center logo of the QR code to the specified logo.

#### 3. Configuring Colors
To configure the colors used in the QR code, use the qrColors function:

```
qrColors {
    // configure colors here
}
```

Within the qrColors block, you can configure the following:

- darkColorBrush: Sets a custom dark color brush with a linear gradient.
```
darkColorBrush = QrKitBrush.customBrush {
    Brush.linearGradient(
        0f to Color.White,
        1f to Color.White,
        end = Offset(it, it)
    )
}
```

#### 4. Configuring Shapes
To configure the shapes used in the QR code, use the qrShapes function:

```
qrShapes {
    // configure shapes here
}
```

Within the qrShapes block, you can configure the following:

- ballShape: Sets the ball shape to a square QR ball.
```
ballShape = getSelectedQrBall(QrBallType.SquareQrBall())
```

- darkPixelShape: Sets the dark pixel shape to a square pixel.
```
darkPixelShape = getSelectedPixel(PixelType.SquarePixel())
```

- frameShape: Sets the frame shape to a square QR frame.
```
frameShape = getSelectedQrFrame(QrFrameType.SquareQrFrame())
```

- qrCodePattern: Sets the QR code pattern to a square pattern.
```
qrCodePattern = getSelectedPattern(PatternType.SquarePattern)
```

### Data Types for QR Codes
Supports a variety of data types for your QR codes:

```
val email: String = email("developer@gmail.com", "addeveloper@gmail.com", "Lorem ipsum", "Lorem ipsum")

val painter = rememberQrKitPainter(data = email)

Image(
    painter = painter, contentDescription = null, modifier = Modifier.size(100.dp)
)
```

1. text: Simple text data type.
2. phone: Telephone number data type.
3. email: Email address data type.
4. sms: Short message service data.
5. wifi: Wireless network configuration data.
6. event: Calendar event data type.
7. location: Geographic location data type.

For Example follow [QrGeneratorScreen.kt](https://github.com/Chaintech-Network/QRKitComposeMultiplatform_org/blob/qrkit/composeApp/src/commonMain/kotlin/org/qrcodedemo/app/ui/QrGeneratorScreen.kt)


For Image Picker we had used this [CMP Pick n Crop Library](https://github.com/Chaintech-Network/CMP-image-pick-n-crop)

- for more follow this class -> [App.kt](https://github.com/Chaintech-Network/QRKitComposeMultiplatform/blob/main/composeApp/src/commonMain/kotlin/org/qrcodedemo/app/App.kt)
- Want to understand in more detail? Checkout this [Medium Article QRCodeGenerator](https://medium.com/mobile-innovation-network/qrkit-2-0-scan-genrate-customize-716d870c3eae), [Medium Article QRCodeScanner](https://medium.com/mobile-innovation-network/qrkit-barcode-scanning-in-compose-multiplatform-for-android-and-ios-77cf5d84f719)
- Connect with us on [LinkedIn](https://www.linkedin.com/showcase/mobile-innovation-network)
