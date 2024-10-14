[![Maven Central](https://img.shields.io/maven-central/v/network.chaintech/qr-kit.svg)](https://central.sonatype.com/artifact/network.chaintech/qr-kit)
[![Kotlin](https://img.shields.io/badge/kotlin-v2.0.20-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-v1.7.0rc01-blue)](https://github.com/JetBrains/compose-multiplatform)
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
    implementation("network.chaintech:qr-kit:3.0.0")
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
    onFailure: (String) -> Unit
)
```

* `modifier`: Modifier for modifying the layout of the QR scanner.
* `flashlightOn`: Boolean indicating whether the flashlight is turned on.
* `cameraLens`: This parameter allows users to switch between the front and back cameras.
* `openImagePicker`: Boolean indicating whether to launch the picker for selecting images.
* `imagePickerHandler`: Callback invoked to indicate the status of the gallery, whether it's open or closed.
* `onFailure`: Callback invoked when there's a failure during QR code scanning.<br>

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

- ### Designed QR Code Generation
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

  ### 📖 Step by Step Implementation

  #### 1. Creating a QR Code Painter
    - Use the `rememberQrKitPainter` function to create a QR code painter with the specified input text.

       ```kotlin
       val painter = rememberQrKitPainter(inputText)
       ```

  #### 2. Setting the Center Logo
    - Set the center logo of the QR code using the `centerLogo` function.

       ```kotlin
       centerLogo { painter = centerLogo }
       ```

  #### 3. Configuring Colors
    - Use the `qrColors` function to configure the colors of the QR code.

       ```kotlin
       qrColors {
            // configure colors here
           darkColorBrush = QrKitBrush.customBrush {
               Brush.linearGradient(
                   0f to Color.White,
                   1f to Color.White,
                   end = Offset(it, it)
               )
           }
           frameColorBrush = QrKitBrush.solidBrush(Color.Blue)
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
      | **qrCodePattern**   | Sets the QR code pattern to a square pattern.| `qrCodePattern = getSelectedPattern(PatternType.SquarePattern)`   |

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