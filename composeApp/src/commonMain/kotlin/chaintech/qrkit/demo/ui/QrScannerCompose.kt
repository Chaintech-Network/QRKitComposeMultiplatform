package chaintech.qrkit.demo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chaintech.qrkit.demo.LocalSnackBarHostState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import qrkitcomposemultiplatform.composeapp.generated.resources.Res
import qrkitcomposemultiplatform.composeapp.generated.resources.ic_gallery_icon
import qrscanner.QrScanner

@Composable
fun QrScannerCompose() {
    var qrCodeURL by remember { mutableStateOf("") }
    var startBarCodeScan by remember { mutableStateOf(false) }
    var flashlightOn by remember { mutableStateOf(false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    val snackBarHostState = LocalSnackBarHostState.current
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (qrCodeURL.isEmpty() && startBarCodeScan) {
                Column(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(250.dp)
                            .clip(shape = RoundedCornerShape(size = 14.dp))
                            .clipToBounds()
                            .border(2.dp, Color.Gray, RoundedCornerShape(size = 14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        QrScanner(
                            modifier = Modifier
                                .clipToBounds()
                                .clip(shape = RoundedCornerShape(size = 14.dp)),
                            flashlightOn = flashlightOn,
                            launchGallery = launchGallery,
                            onCompletion = {
                                qrCodeURL = it
                                startBarCodeScan = false
                            },
                            onGalleryCallBackHandler = {
                                launchGallery = it
                            },
                            onFailure = {
                                coroutineScope.launch {
                                    if (it.isEmpty()) {
                                        snackBarHostState.showSnackbar("Invalid qr code")
                                    } else {
                                        snackBarHostState.showSnackbar(it)
                                    }
                                }
                            }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                            .background(
                                color = Color(0xFFF9F9F9),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .height(35.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 18.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(11.dp)
                        ) {
                            Icon(imageVector = if (flashlightOn) Icons.Filled.FlashOn else Icons.Filled.FlashOff,
                                "flash",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        flashlightOn = !flashlightOn
                                    })

                            VerticalDivider(
                                modifier = Modifier,
                                thickness = 1.dp,
                                color = Color(0xFFD8D8D8)
                            )

                            Image(
                                painter = painterResource(Res.drawable.ic_gallery_icon),
                                contentDescription = "gallery",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        launchGallery = true
                                    }
                            )
                        }
                    }
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            startBarCodeScan = true
                            qrCodeURL = ""
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
                    ) {
                        Text(
                            text = "Scan Qr",
                            modifier = Modifier.background(Color.Transparent)
                                .padding(horizontal = 12.dp, vertical = 12.dp),
                            fontSize = 16.sp
                        )
                    }

                    Text(
                        text = qrCodeURL,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }
        if (startBarCodeScan) {
            Icon(
                imageVector = Icons.Filled.Close,
                "Close",
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
                    .size(24.dp)
                    .clickable {
                        startBarCodeScan = false
                    }.align(Alignment.TopEnd),
                tint = Color.White
            )
        }
    }

}