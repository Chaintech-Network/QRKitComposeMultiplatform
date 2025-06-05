package org.qrcodedemo.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.graphics.Color
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
import org.qrcodedemo.app.platformName
import qrcodedemo.composeapp.generated.resources.Res
import qrcodedemo.composeapp.generated.resources.ic_camera_switch
import qrcodedemo.composeapp.generated.resources.ic_gallery_icon
import qrcodedemo.composeapp.generated.resources.ic_rectangle
import qrcodedemo.composeapp.generated.resources.ic_square_border
import qrscanner.CameraLens
import qrscanner.OverlayShape
import qrscanner.QrScanner

@Composable
fun QrScannerView(onNavigate: (String) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val zoomLevels = listOf(1f, 2f, 3f)
    var selectedZoomIndex = 0

    var qrCodeURL by remember { mutableStateOf("") }
    var flashlightOn by remember { mutableStateOf(false) }
    var openImagePicker by remember { mutableStateOf(false) }
    var overlayShape by remember { mutableStateOf(OverlayShape.Square) }
    var cameraLens by remember { mutableStateOf(CameraLens.Back) }
    var currentZoomLevel by remember { mutableStateOf(zoomLevels[selectedZoomIndex]) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .background(Color(0xFF1D1C22))
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // QR Scanner Camera Preview
            QrScanner(
                modifier = Modifier.fillMaxSize(),
                flashlightOn = flashlightOn,
                cameraLens = cameraLens,
                openImagePicker = openImagePicker,
                onCompletion = { qrCodeURL = it },
                zoomLevel = currentZoomLevel,
                maxZoomLevel = 3f,
                imagePickerHandler = { openImagePicker = it },
                onFailure = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(it.ifEmpty { "Invalid QR Code" })
                    }
                },
                overlayShape = overlayShape
            )

            if (platformName() != "Desktop") {
                BottomActions(
                    flashlightOn = flashlightOn,
                    onToggleFlash = {
                        if (cameraLens == CameraLens.Front) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("Flash not available in front camera")
                            }
                        } else {
                            flashlightOn = !flashlightOn
                        }
                    },
                    onSwitchCamera = {
                        cameraLens = if (cameraLens == CameraLens.Front) {
                            flashlightOn = false
                            CameraLens.Back
                        } else CameraLens.Front
                    },
                    onOpenGallery = { openImagePicker = true },
                    onShapeChange = { overlayShape = it },
                    onZoomLevelChange = {
                        if (selectedZoomIndex == (zoomLevels.size-1)) {
                            selectedZoomIndex = 0
                        } else {
                            selectedZoomIndex+= 1
                        }
                        currentZoomLevel = zoomLevels[selectedZoomIndex]
                    }
                )
            } else {
                Button(
                    modifier = Modifier.align(Alignment.Center).padding(top = 12.sdp),
                    onClick = { openImagePicker = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5144D8))
                ) {
                    Text(
                        text = "Select Image",
                        fontSize = 16.ssp,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(horizontal = 12.sdp, vertical = 12.sdp)
                    )
                }
            }

            if (qrCodeURL.isNotEmpty()) {
                ResultDisplay(
                    result = qrCodeURL,
                    onCopy = {
                        clipboardManager.setText(AnnotatedString(qrCodeURL))
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Copied")
                        }
                    }
                )
            }

            TopBar(onClose = { onNavigate(AppConstants.BACK_CLICK_ROUTE) })
        }
    }
}


@Composable
fun BoxScope.BottomActions(
    flashlightOn: Boolean,
    onToggleFlash: () -> Unit,
    onSwitchCamera: () -> Unit,
    onOpenGallery: () -> Unit,
    onShapeChange: (OverlayShape) -> Unit,
    onZoomLevelChange: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 20.sdp, end = 20.sdp, top = 20.sdp, bottom = 150.sdp)
            .align(Alignment.BottomCenter)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFF9F9F9), RoundedCornerShape(25.sdp))
                .height(35.sdp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.padding(vertical = 4.sdp, horizontal = 16.sdp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.sdp)
            ) {
                Icon(
                    imageVector = if (flashlightOn) Icons.Filled.FlashOn else Icons.Filled.FlashOff,
                    contentDescription = "Flash",
                    modifier = Modifier.size(20.sdp).clickable(onClick = onToggleFlash)
                )
                DividerDot()
                Icon(
                    imageVector = Icons.Filled.ZoomIn,
                    contentDescription = "Zoom",
                    modifier = Modifier.size(20.sdp).clickable(onClick = {
                        onZoomLevelChange()
                    })
                )
                DividerDot()
                Image(
                    painter = painterResource(Res.drawable.ic_camera_switch),
                    contentDescription = "Switch",
                    modifier = Modifier.size(20.sdp).clickable(onClick = onSwitchCamera)
                )
                DividerDot()
                Image(
                    painter = painterResource(Res.drawable.ic_gallery_icon),
                    contentDescription = "Gallery",
                    modifier = Modifier.size(20.sdp).clickable(onClick = onOpenGallery)
                )
                DividerDot()
                Icon(
                    painter = painterResource(Res.drawable.ic_square_border),
                    contentDescription = "Square",
                    modifier = Modifier.size(20.sdp).clickable { onShapeChange(OverlayShape.Square) }
                )
                DividerDot()
                Icon(
                    painter = painterResource(Res.drawable.ic_rectangle),
                    contentDescription = "Rectangle",
                    modifier = Modifier.size(20.sdp).clickable { onShapeChange(OverlayShape.Rectangle) }
                )
            }
        }
    }
}

@Composable
private fun DividerDot() {
    VerticalDivider(
        thickness = 1.sdp,
        color = Color(0xFFD8D8D8)
    )
}

@Composable
fun BoxScope.ResultDisplay(result: String, onCopy: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 14.sdp)
            .padding(bottom = 30.sdp)
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = result,
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
            imageVector = Icons.Filled.CopyAll,
            contentDescription = "Copy",
            modifier = Modifier.size(20.sdp).clickable(onClick = onCopy),
            tint = Color.White
        )
    }
}

@Composable
fun TopBar(onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 14.sdp, vertical = 20.sdp)
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
            imageVector = Icons.Filled.Close,
            contentDescription = "Close",
            modifier = Modifier.size(20.sdp).clickable(onClick = onClose),
            tint = Color.White
        )
    }
}
