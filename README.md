[![Maven Central](https://img.shields.io/maven-central/v/network.chaintech/qr-kit.svg)](https://central.sonatype.com/artifact/network.chaintech/qr-kit)
[![Kotlin](https://img.shields.io/badge/kotlin-v2.0.20-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-v1.7.0-blue)](https://github.com/JetBrains/compose-multiplatform)
[![License](https://img.shields.io/github/license/Chaintech-Network/CMPCharts)](http://www.apache.org/licenses/LICENSE-2.0)

![badge-android](http://img.shields.io/badge/platform-android-3DDC84.svg?style=flat)
![badge-ios](http://img.shields.io/badge/platform-ios-FF375F.svg?style=flat)
![badge-desktop](http://img.shields.io/badge/platform-desktop-FF9500.svg?style=flat)

# QRKit - Compose Multiplatform
QRKit is a Compose Multiplatform library designed for seamless QR code scanning & QR code Generating across Android, iOS, and Desktop applications.

<img width="960" alt="QR-code-generator-scanner" src="/assets/CMP_QRKit_scanner_genrator_banner.png">

## 📦 Installation

To use QRKit in your Compose Multiplatform project, add the following dependency to your `build.gradle.kts` file

```kotlin
commonMain.dependencies {
    implementation("network.chaintech:qr-kit:3.0.8")
}
```   
<br>

## 🚀 Usage

### 🔒 Add Permissions in Android and iOS

- **Android**: Include this at the root level in your `AndroidManifest.xml`:

    ```xml
    <uses-feature android:name="android.hardware.camera"/>
    <uses-feature android:name="android.hardware.camera.autofocus"/>
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.FLASHLIGHT"/>
    ```

- **iOS**: Add the following keys to the `Info.plist` in your Xcode project:

    ```xml
    <key>NSCameraUsageDescription</key><string>$(PRODUCT_NAME) camera description.</string>
    <key>NSPhotoLibraryUsageDescription</key><string>$(PRODUCT_NAME) photos description.</string>
    ```

### 📷 QR Code Scanner

<img width="960" alt="QR-code-generator-scanner" src="/assets/CMP_QRKit_scanner.gif">

```kotlin
QrScanner(
    modifier: Modifier,
    flashlightOn: Boolean,
    cameraLens: CameraLens,
    openImagePicker: Boolean,
    onCompletion: (String) -> Unit,
    imagePickerHandler: (Boolean) -> Unit,
    onFailure: (String) -> Unit,
    overlayShape = OverlayShape.Square,
    overlayColor = Color(0x88000000),
    overlayBorderColor = Color.White,
    customOverlay: (ContentDrawScope.() -> Unit)? = null,
    permissionDeniedView: @Composable (() -> Unit?)? = null
)
```

* `modifier`: Modifier for modifying the layout of the QR scanner.
* `flashlightOn`: Boolean indicating whether the flashlight is turned on.
* `cameraLens`: This parameter allows users to switch between the front and back cameras.
* `openImagePicker`: Boolean indicating whether to launch the picker for selecting images.
* `imagePickerHandler`: Callback invoked to indicate the status of the gallery, whether it's open or closed.
* `onFailure`: Callback invoked when there's a failure during QR code scanning.<br>
* `overlayShape`: Defines the shape of the overlay that appears over the camera view. Square and Rectangle<br>
* `overlayColor`: The color of the overlay that dims the area around the scanning region. The default is a semi-transparent black color.<br>
* `overlayBorderColor`: The color of the border around the scanning region. The default is white.<br>
* `customOverlay`: Allows the user to provide a custom overlay design. If no custom overlay is provided, a default one is applied.<br>
* `permissionDeniedView`: Allows the user to provide a custom view when camera permission is denied. If no custom view is provided, a default one is shown.<br>

🔗 For more details, follow the implementation in [QrScannerScreen.kt](https://github.com/Chaintech-Network/QRKitComposeMultiplatform_org/blob/qrkit/composeApp/src/commonMain/kotlin/org/qrcodedemo/app/ui/QrScannerScreen.kt)

<br>

### 🖼️ QR Code Generator

<img width="960" alt="QRCodeGenerator" src="/assets/CMP_QRKit_QR_Generator.png">
<img width="960" alt="QRCodeCustomization" src="/assets/CMP_QRKit_QR_Customisation.png">

- ### Basic QR Code Generation
  Generating a basic QR code is as simple as using the rememberQrKitPainter function:

    ```kotlin
    val painter = rememberQrKitPainter(data = inputText)
    
    Image(
        painter = painter, 
        contentDescription = null, 
        modifier = Modifier.size(100.dp)
    )
    ```

- ### Sharable QR Code Generation
  Generate a Sharable QR code using the QRCodeImage function. Here you will get a bitmap file which can be shared:

    ```kotlin
    var qrImageBitmap: ImageBitmap? = null
    
    QRCodeImage(
       modifier = Modifier.size(130.sdp),
       url = inputText,
       contentDescription = "Sharable QR Code",
       onSuccess = { qrImage ->
           qrImage?.let {
               qrImageBitmap = it
           }
       }
    )
  
    // To share the QR Code use the below code
    shareQrCodeImage(qrImageBitmap, "QR Code")
    ```

- ### Designed QR Code Generation
  To create a more customized QR code, you can use rememberQrKitPainter function with additional options:

    ```kotlin
    val centerLogo = painterResource(Res.drawable.ic_youtube)
  
    val painter = rememberQrKitPainter(inputText) {
        shapes = QrKitShapes(
            ballShape = getSelectedQrBall(QrBallType.SquareQrBall()),
            darkPixelShape = getSelectedPixel(QrPixelType.SquarePixel()),
            frameShape = getSelectedFrameShape(QrFrameType.SquareFrame()),
            codeShape = getSelectedPattern(PatternType.SquarePattern),
        )
        colors = QrKitColors(
            darkBrush = QrKitBrush.customBrush {
                Brush.linearGradient(
                    0f to color[0],
                    1f to color[1],
                    end = Offset(it, it)
                )
            }
        )
        logo = QrKitLogo(centerLogo)
    }
    ```

  ### 📖 Step by Step Implementation

  #### 1. Creating a QR Code Painter
    - Use the `rememberQrKitPainter` function to create a QR code painter with the specified input text.

       ```kotlin
       val painter = rememberQrKitPainter(inputText)
       ```

  #### 2. Setting the Center Logo
    - Set the center logo of the QR code using the `QrKitLogo` function.

       ```kotlin
       QrKitLogo(centerLogo)
       ```

  #### 3. Configuring Colors
    - Use the `qrColors` function to configure the colors of the QR code.

       ```kotlin
       qrColors {
            // configure colors here
           darkBrush = QrKitBrush.customBrush {
               Brush.linearGradient(
                   0f to Color.White,
                   1f to Color.White,
                   end = Offset(it, it)
               )
           }
           frameBrush = QrKitBrush.solidBrush(Color.Blue)
       }
       ```

  #### 4. Configuring Shapes
    - Use the `qrShapes` function to configure the shapes used in the QR code.

       ```kotlin
       qrShapes {
           // configure shapes here
       }
       ```

      Within the `qrShapes` block, you can configure the following properties:

      | Property            | Description                                 | Example Code                                                      |
      |---------------------|---------------------------------------------|-------------------------------------------------------------------|
      | **ballShape**       | Sets the ball shape to a square QR ball.   | `ballShape = getSelectedQrBall(QrBallType.SquareQrBall())`      |
      | **darkPixelShape**  | Sets the dark pixel shape to a square pixel.| `darkPixelShape = getSelectedPixel(PixelType.SquarePixel())`     |
      | **frameShape**      | Sets the frame shape to a square QR frame. | `frameShape = getSelectedQrFrame(QrFrameType.SquareQrFrame())`   |
      | **codeShape**   | Sets the QR code pattern to a square pattern.| `codeShape = getSelectedPattern(PatternType.SquarePattern)`   |

<br>

- ### Supported Data Types for QR Codes
  The QR code generator supports a wide range of data types, allowing you to encode various forms of information effortlessly.


    | Data Type | Description                                 |
    |-----------|---------------------------------------------|
    | **Text**  | Simple text data type.                      |
    | **Phone** | Telephone number data type.                 |
    | **Email** | Email address data type.                    |
    | **SMS**   | Short message service data.                 |
    | **Wi-Fi** | Wireless network configuration data.        |
    | **Event** | Calendar event data type.                   |
    | **Location** | Geographic location data type.            |
    
    Below is an example of how to create a QR code for an email address:
    
    ```kotlin
    val email: String = email("developer@gmail.com", "addeveloper@gmail.com", "Lorem ipsum", "Lorem ipsum")
    
    val painter = rememberQrKitPainter(data = email)
    
    Image(
        painter = painter, contentDescription = null, modifier = Modifier.size(100.dp)
    )
    ```

<br><br>

## 🏷️ Barcode Generator
<img width="960" alt="QRCodeGenerator" src="/assets/CMP_QRKit_Barcode_Generator.png">

You can easily generate and display barcodes using the `rememberBarcodePainter` function in your Compose application.

```kotlin
val barCodePainter = rememberBarcodePainter(
    content = data, 
    format = type, 
    brush = SolidColor(Color.White), 
    onError = { throwable ->
        scope.launch {
            snackbarHostState.showSnackbar("Error occurred: ${throwable.message}")
        }
        // Return a fallback painter, for example, an empty painter
        EmptyPainter()
    }
)

Image(
    painter = barCodePainter,
    contentDescription = null,
    modifier = Modifier
        .fillMaxWidth()
        .height(100.sdp)
)
```

<br>🔗 For more details, follow the implementation in [BarCodeGeneratorScreen.kt](https://github.com/Chaintech-Network/QRKitComposeMultiplatform_org/blob/qrkit/composeApp/src/commonMain/kotlin/org/qrcodedemo/app/ui/BarCodeGeneratorScreen.kt)

<br>The following table explains the parameters used in the `rememberBarcodePainter` function.

| Parameter       | Description                                                                                                       |
|-----------------|-------------------------------------------------------------------------------------------------------------------|
| `content`       | The string data to be encoded into the barcode.                                                                   |
| `format`        | The format of the barcode to be generated (e.g., `BarcodeFormat.QR_CODE`, `CODE_128`, etc.).                      |
| `brush`         | The `Brush` used to paint the barcode. By default, it uses `SolidColor(Color.Black)`.                             |
| `onError`       | A callback function that handles errors during barcode generation. It returns a `Painter` to display in case of failure.|
| `pathGenerator` | Function that generates the path for the barcode. It defines how the barcode lines or patterns are drawn. The default is `::defaultBarcodePathGenerator`. |
| `factory`       | A `PainterFactory` used to create the `Painter` for rendering the barcode. The default is `DefaultPainterFactory()`.|

<br>

### 🧩 Barcode Formats

| Barcode Format | Description | Limitations |
|----------------|-------------|-------------|
| `Codabar`      | **Codabar** is a simple barcode used for libraries, blood banks, and airbills. | Can encode numbers and a few special characters (A, B, C, D, +, -, $, /, :). Limited character set. |
| `Code39`       | **Code 39** is widely used in industrial applications. It can encode alphanumeric characters and a few special symbols. | Lower data density compared to other formats. |
| `Code93`       | **Code 93** is an enhanced version of Code 39, offering higher density and encoding of additional characters. | Limited to alphanumeric data, but can encode more characters than Code 39. |
| `Code128`      | **Code 128** is a high-density barcode used for logistics and shipping. It can encode all 128 ASCII characters. | More complex and longer compared to simpler formats like Code 39. |
| `EAN8`         | **EAN-8** is a shorter version of EAN-13, used on smaller products where space is limited. | Can encode only 8 digits, limiting the amount of information. |
| `EAN13`        | **EAN-13** is the standard barcode for products, mainly used in retail. | Can encode only numeric digits, no letters or symbols. |
| `ITF`          | **ITF (Interleaved 2 of 5)** is used in packaging and logistics. | Can encode only numeric digits and may be prone to misreading without proper scanning equipment. |
| `UPCA`         | **UPC-A** is a barcode format used for retail products in the US, encoding 12 digits. | Can encode only numeric digits and space is limited to 12 characters. |
| `UPCE`         | **UPC-E** is a compressed version of UPC-A, used on smaller packages. | Limited to encoding fewer digits (6–8) and primarily used in specific retail scenarios. |





<br><br>

## 📖 Detailed Explanation

✨ **See the QR Code Generator in Action!**
- Check out the full implementation in [QrGeneratorScreen.kt](https://github.com/Chaintech-Network/QRKitComposeMultiplatform_org/blob/qrkit/composeApp/src/commonMain/kotlin/org/qrcodedemo/app/ui/QrGeneratorScreen.kt) to witness the magic of generating QR codes in real-time! 🚀

💡 **Need to Pick and Crop Images?**
- We’ve integrated the powerful **CMP Pick n Crop Library** for seamless image handling. Head over to [App.kt](https://github.com/Chaintech-Network/QRKitComposeMultiplatform/blob/main/composeApp/src/commonMain/kotlin/org/qrcodedemo/app/App.kt) for all the details! 📸

📖 **Want to Learn More?**  
Explore our detailed Medium articles for a deeper dive:

- [**QRCodeGenerator**](https://medium.com/mobile-innovation-network/qrkit-2-0-scan-genrate-customize-716d870c3eae) 🛠️ — Everything you need to know about generating QR codes!
- [**QRCodeScanner**](https://medium.com/mobile-innovation-network/qrkit-barcode-scanning-in-compose-multiplatform-for-android-and-ios-77cf5d84f719) 🔍 — Learn how to implement QR code scanning across platforms!
  <br><br>

## 🌐 Stay Connected with Us
Stay connected and keep up with our latest innovations! 💼 Let's innovate together!<br><br>
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/showcase/mobile-innovation-network)
[![Medium](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/mobile-innovation-network)   
<br>

## 📄 License
```
Copyright 2023 Mobile Innovation Network

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```