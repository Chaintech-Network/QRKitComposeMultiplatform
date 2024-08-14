package org.qrcodedemo.app.ui

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.launch
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import org.qrcodedemo.app.LocalSnackBarHostState
import org.qrcodedemo.app.platformName
import qrcodedemo.composeapp.generated.resources.Res
import qrcodedemo.composeapp.generated.resources.ic_gallery_icon
import qrscanner.QrScanner

@Composable
fun QrScannerView(onNavigate: (String) -> Unit) {
    var qrCodeURL by remember { mutableStateOf("") }
    var flashlightOn by remember { mutableStateOf(false) }
    var openImagePicker by remember { mutableStateOf(value = false) }
    val snackBarHostState = LocalSnackBarHostState.current
    val coroutineScope = rememberCoroutineScope()
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Box(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        Column(
            modifier = Modifier
                .background(Color(0xFF1D1C22))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = if (platformName() != "Desktop") {
                    Modifier
                        .size(200.sdp)
                        .clip(shape = RoundedCornerShape(size = 14.sdp))
                        .clipToBounds()
                        .border(2.sdp, Color.Gray, RoundedCornerShape(size = 14.sdp))
                } else {
                    Modifier
                },
                contentAlignment = Alignment.Center
            ) {
                QrScanner(
                    modifier = Modifier
                        .clipToBounds()
                        .clip(shape = RoundedCornerShape(size = 14.sdp)),
                    flashlightOn = flashlightOn,
                    openImagePicker = openImagePicker,
                    onCompletion = {
                        qrCodeURL = it
                    },
                    imagePickerHandler = {
                        openImagePicker = it
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

            if (platformName() != "Desktop") {
                Box(
                    modifier = Modifier
                        .padding(start = 20.sdp, end = 20.sdp, top = 20.sdp)
                        .background(
                            color = Color(0xFFF9F9F9),
                            shape = RoundedCornerShape(25.sdp)
                        )
                        .height(35.sdp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .padding(vertical = 4.sdp, horizontal = 16.sdp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.sdp)
                    ) {
                        Icon(
                            imageVector = if (flashlightOn) Icons.Filled.FlashOn else Icons.Filled.FlashOff,
                            "flash",
                            modifier = Modifier
                                .size(20.sdp)
                                .clickable {
                                    flashlightOn = !flashlightOn
                                }
                        )

                        VerticalDivider(
                            modifier = Modifier,
                            thickness = 1.sdp,
                            color = Color(0xFFD8D8D8)
                        )

                        Image(
                            painter = painterResource(Res.drawable.ic_gallery_icon),
                            contentDescription = "gallery",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(20.sdp)
                                .clickable {
                                    openImagePicker = true
                                }
                        )
                    }
                }
            } else {
                Button(
                    modifier = Modifier.padding(top = 12.sdp),
                    onClick = {
                        openImagePicker = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5144D8)
                    ),
                ) {
                    Text(
                        text = "Select Image",
                        modifier = Modifier.background(Color.Transparent)
                            .padding(horizontal = 12.sdp, vertical = 12.sdp),
                        fontSize = 16.ssp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }

        if (qrCodeURL.isNotEmpty()) {
            Row(
                modifier = Modifier.padding(horizontal = 14.sdp).padding(bottom = 22.sdp)
                    .fillMaxWidth()
                    .align(
                        Alignment.BottomCenter
                    ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = qrCodeURL,
                    modifier = Modifier
                        .padding(end = 8.sdp)
                        .weight(1f),
                    fontSize = 12.ssp,
                    color = Color.White,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )

                Icon(
                    Icons.Filled.CopyAll,
                    "CopyAll",
                    modifier = Modifier.size(20.sdp).clickable {
                        clipboardManager.setText(AnnotatedString((qrCodeURL)))
                    },
                    tint = Color.White
                )
            }
        }

        Row(
            modifier = Modifier.padding(start = 14.sdp, end = 14.sdp, top = 20.sdp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "QRScanner",
                modifier = Modifier.weight(1f),
                fontSize = 18.ssp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            Icon(
                Icons.Filled.Close,
                "close",
                modifier = Modifier.size(20.sdp).clickable {
                    onNavigate(AppConstants.BACK_CLICK_ROUTE)
                },
                tint = Color.White
            )
        }
    }
}