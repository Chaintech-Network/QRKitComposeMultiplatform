package org.qrcodedemo.app.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.qrcodedemo.app.LocalSnackBarHostState
import qrcodedemo.composeapp.generated.resources.img
import qrgenerator.QRCodeImage
import qrgenerator.generateQrCode
import qrgenerator.qrpainter.QrBallShape
import qrgenerator.qrpainter.QrBrush
import qrgenerator.qrpainter.QrCodePainter
import qrgenerator.qrpainter.QrFrameShape
import qrgenerator.qrpainter.QrLogoPadding
import qrgenerator.qrpainter.QrLogoShape
import qrgenerator.qrpainter.QrPixelShape
import qrgenerator.qrpainter.brush
import qrgenerator.qrpainter.circle
import qrgenerator.qrpainter.rememberQrCodePainter
import qrgenerator.qrpainter.roundCorners
import qrgenerator.qrpainter.solid

@Composable
fun QrGeneratorCompose(paddingValues: PaddingValues) {
    val scope = rememberCoroutineScope()
    var inputText by rememberSaveable { mutableStateOf("") }
    var isInputTextError by rememberSaveable { mutableStateOf(false) }
    val generatedQRCode = remember { mutableStateOf<ImageBitmap?>(null) }
    val focusManager = LocalFocusManager.current
    val snackBarHostState = LocalSnackBarHostState.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .padding(top = 22.dp)
            .verticalScroll(rememberScrollState())
    ) {
//        QRCodeImage(
//            url = "https://www.google.com/",
//            contentScale = ContentScale.Fit,
//            contentDescription = "QR Code",
//            modifier = Modifier
//                .size(150.dp),
//            onSuccess = { qrImage ->
//
//            },
//            onFailure = {
//                scope.launch {
//                    snackBarHostState.showSnackbar("Something went wrong")
//                }
//            }
//        )

//        QrCode("https://www.google.com/") { painter ->
//            Image(
//                painter = painter,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(150.dp)
//            )
//        }

        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
                isInputTextError = inputText.isBlank()
            },
            label = { Text("Please enter text", style = MaterialTheme.typography.titleMedium) },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = false,
            isError = isInputTextError,
            modifier = Modifier.padding(top = 14.dp).fillMaxWidth(),
            shape = RoundedCornerShape(10),
            trailingIcon = {
                if (isInputTextError) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = Color.Red
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(22.dp))

//        Button(
//            onClick = {
//                if (inputText.isBlank()) {
//                    isInputTextError = true
//                    return@Button
//                } else {
//                    focusManager.clearFocus()
//                    generateQrCode(inputText, onSuccess = { info, qrCode ->
//                        generatedQRCode.value = qrCode
//                    }, onFailure = {
//                        scope.launch {
//                            snackBarHostState.showSnackbar("Something went wrong")
//                        }
//                    })
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(54.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
//            shape = RoundedCornerShape(25)
//        ) {
//            Text(
//                text = "Generate code",
//                style = MaterialTheme.typography.bodyLarge,
//                color = Color.White
//            )
//        }
        if (inputText.isEmpty().not()) {
            QrCode(inputText) { painter ->
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                )
            }
        }
//        generatedQRCode.value?.let { qrCode ->
//            QRCodeViewer(qrCode)
//        }
    }
}

@Composable
fun QRCodeViewer(qrCode: ImageBitmap) {
    var isLoading by rememberSaveable { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(500)
        isLoading = false
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 22.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.White)
                .border(BorderStroke(3.dp, Color.Black))
                .size(250.dp)
        ) {
            if (isLoading) {
                QRCodeShimmer()
                return@Column
            }

            Image(
                bitmap = qrCode,
                contentScale = ContentScale.Fit,
                contentDescription = "QR Code",
                modifier = Modifier
                    .fillMaxSize(1f)
            )
        }
    }
}

@Composable
fun QRCodeShimmer() {
    val shimmerColorShades = listOf(
        Color.Gray.copy(0.5f),
        Color.LightGray.copy(0.2f),
        Color.Gray.copy(0.5f),
    )

    val transition = rememberInfiniteTransition()
    val transitionAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(transitionAnim, transitionAnim)
    )

    Surface(
        color = Color.Transparent,
        modifier = Modifier.alpha(.3f)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(300.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = brush)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxSize(.8f)
                    .background(brush = brush)
            )
        }
    }
}

@Composable
fun QrCode(data: String, content: @Composable() (QrCodePainter) -> Unit) {
    val logo = painterResource(qrcodedemo.composeapp.generated.resources.Res.drawable.img)

    val painter = rememberQrCodePainter(data) {
        logo {
            painter = logo
            padding = QrLogoPadding.Natural(.1f)
            shape = QrLogoShape.circle()
            size = 0.2f
        }

        shapes() {
            ball = QrBallShape.circle()
            darkPixel = QrPixelShape.roundCorners()
            frame = QrFrameShape.roundCorners(.25f)
        }

        colors {
            dark = QrBrush.brush {
                Brush.linearGradient(
                    0f to Color.Red,
                    1f to Color.Blue,
                    end = Offset(it, it)
                )
            }
            frame = QrBrush.solid(Color.Black)
        }
    }

    content(painter)
}