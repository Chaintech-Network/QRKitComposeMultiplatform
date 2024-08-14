package org.qrcodedemo.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import qrcodedemo.composeapp.generated.resources.Res
import qrcodedemo.composeapp.generated.resources.bg
import qrcodedemo.composeapp.generated.resources.ic_calendar
import qrcodedemo.composeapp.generated.resources.ic_done
import qrcodedemo.composeapp.generated.resources.ic_drop_down
import qrcodedemo.composeapp.generated.resources.ic_email
import qrcodedemo.composeapp.generated.resources.ic_magic_tool
import qrcodedemo.composeapp.generated.resources.ic_phone
import qrcodedemo.composeapp.generated.resources.ic_scanner
import qrcodedemo.composeapp.generated.resources.ic_sms
import qrcodedemo.composeapp.generated.resources.ic_text
import qrcodedemo.composeapp.generated.resources.ic_text_blue
import qrcodedemo.composeapp.generated.resources.ic_website
import qrcodedemo.composeapp.generated.resources.ic_wifi
import qrgenerator.qrkitpainter.QrKitBrush
import qrgenerator.qrkitpainter.customBrush
import qrgenerator.qrkitpainter.rememberQrKitPainter

@Composable
fun QrGeneratorView(onNavigate: (String) -> Unit) {
    var selectedType by remember { mutableStateOf("Text") }
    var selectedIndex by remember { mutableStateOf(0) }
    var showSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(Color(0xFF1D1C22))
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(resource = Res.drawable.bg),
            contentScale = ContentScale.Fit,
            contentDescription = ""
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 120.sdp)
        ) {
            Text(
                text = "QR Code",
                modifier = Modifier
                    .padding(horizontal = 14.sdp)
                    .padding(top = 20.sdp),
                style = TextStyle(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF27AD9D), Color(0xFFC9F31D)
                        )
                    )
                ),
                fontSize = 14.ssp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )

            Text(
                text = "Generator",
                modifier = Modifier
                    .padding(horizontal = 14.sdp)
                    .padding(top = 2.sdp),
                fontSize = 26.ssp,
                style = TextStyle(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF27AD9D), Color(0xFF4D8EFF)
                        )
                    )
                ),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            Text(
                text = "Choose QR Code type",
                modifier = Modifier
                    .padding(horizontal = 14.sdp)
                    .padding(top = 22.sdp),
                fontSize = 12.ssp,
                color = Color(0xFFAFAFAF),
                fontFamily = FontFamily.Serif
            )

            OutlinedTextField(value = selectedType,
                modifier = Modifier
                    .padding(horizontal = 14.sdp)
                    .padding(top = 10.sdp)
                    .fillMaxWidth()
                    .clickable {
                        showSheet = true
                    },
                onValueChange = {
                    selectedType = it
                },
                enabled = false,
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Text",
                        modifier = Modifier,
                        fontSize = 12.ssp,
                        color = Color(0xFF747474),
                        fontFamily = FontFamily.Serif
                    )
                },
                textStyle = TextStyle(
                    fontSize = 12.ssp, color = Color.White, fontFamily = FontFamily.Serif
                ),
                shape = RoundedCornerShape(6.sdp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color(0xFF3E3D46),
                    focusedBorderColor = Color(0xFF3E3D46),
                    cursorColor = Color.White,
                    disabledBorderColor = Color(0xFF3E3D46),
                ),
                leadingIcon = {
                    Image(
                        modifier = Modifier.padding(start = 10.sdp, end = 10.sdp).size(18.sdp),
                        painter = painterResource(resource = Res.drawable.ic_text),
                        contentScale = ContentScale.Fit,
                        contentDescription = ""
                    )
                },
                trailingIcon = {
                    Image(
                        modifier = Modifier.padding(end = 8.sdp).size(8.sdp),
                        painter = painterResource(resource = Res.drawable.ic_drop_down),
                        contentScale = ContentScale.Fit,
                        contentDescription = ""
                    )
                })

            when (selectedType) {
                "Text" -> {
                    TextQR(onNavigate)
                }

                "Website" -> {
                    WebsiteQR(onNavigate)
                }

                "Email" -> {
                    EmailQR(onNavigate)
                }

                "SMS" -> {
                    SMSQR(onNavigate)
                }

                "Wi-Fi" -> {
                    WifiQR(onNavigate)
                }

                "Phone" -> {
                    PhoneQR(onNavigate)
                }

                "Calendar" -> {
                    CalendarQR(onNavigate)
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(bottom = 22.sdp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF6468DF), Color(0xFF5144D8)
                        )
                    ), RoundedCornerShape(40.sdp)
                )
                .border(1.sdp, Color.White, RoundedCornerShape(40.sdp))
                .padding(vertical = 8.sdp, horizontal = 20.sdp)
                .align(Alignment.BottomCenter)
                .clickable {
                    onNavigate(AppScreen.QRScanner.route)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(20.sdp),
                painter = painterResource(resource = Res.drawable.ic_scanner),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )
            Text(
                text = "Scan QR Code",
                modifier = Modifier
                    .padding(start = 8.sdp),
                fontSize = 12.ssp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        }
    }

    if (showSheet) {
        QRTypeBottomSheet(
            selectedIndex = selectedIndex,
            callBack = { index, data ->
                showSheet = false
                selectedIndex = index
                selectedType = data.title
            },
            onDismiss = { showSheet = false }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRTypeBottomSheet(
    selectedIndex: Int,
    callBack: (Int, QRType) -> Unit,
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        containerColor = Color.Transparent,
        dragHandle = {}
    ) {
        val list = arrayListOf<QRType>()
        list.add(QRType(Res.drawable.ic_text_blue, "Text"))
        list.add(QRType(Res.drawable.ic_website, "Website"))
        list.add(QRType(Res.drawable.ic_email, "Email"))
        list.add(QRType(Res.drawable.ic_sms, "SMS"))
        list.add(QRType(Res.drawable.ic_wifi, "Wi-Fi"))
        list.add(QRType(Res.drawable.ic_phone, "Phone"))
        list.add(QRType(Res.drawable.ic_calendar, "Calendar"))

        Column(
            modifier = Modifier
                .padding(14.sdp)
                .background(Color(0xFF18171C), RoundedCornerShape(12.sdp))
                .padding(14.sdp)
        ) {
            HorizontalDivider(
                modifier = Modifier
                    .width(40.sdp)
                    .clip(RoundedCornerShape(50.sdp))
                    .align(Alignment.CenterHorizontally), 3.sdp, color = Color(0xFF383737)
            )

            Text(
                text = "Choose QR Code type",
                modifier = Modifier.padding(top = 20.sdp),
                fontSize = 14.ssp,
                color = Color(0xFF7A7AD9),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )

            LazyColumn(modifier = Modifier.padding(top = 20.sdp)) {
                itemsIndexed(list) { index, data ->
                    Column(modifier = Modifier.fillMaxWidth().padding(top = 4.sdp).clickable {
                        callBack(index, data)
                    }) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(vertical = 8.sdp, horizontal = 4.sdp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(18.sdp),
                                painter = painterResource(resource = data.icon),
                                contentScale = ContentScale.Fit,
                                contentDescription = ""
                            )
                            Text(
                                text = data.title,
                                modifier = Modifier.padding(start = 14.sdp).weight(1f),
                                fontSize = 12.ssp,
                                color = Color(0xFFAFAFAF),
                                fontWeight = FontWeight.Medium,
                                fontFamily = FontFamily.Serif
                            )
                            if (selectedIndex == index) Image(
                                modifier = Modifier.size(16.sdp),
                                painter = painterResource(resource = Res.drawable.ic_done),
                                contentScale = ContentScale.Fit,
                                contentDescription = ""
                            )
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(top = 4.sdp).fillMaxWidth(),
                            1.sdp,
                            color = Color(0xFF24222C)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QREditTextView(
    labelText: String, placeHolder: String, inputText: String, onValueChange: (String) -> Unit
) {
    Text(
        text = labelText,
        modifier = Modifier.padding(horizontal = 14.sdp).padding(top = 20.sdp),
        fontSize = 12.ssp,
        color = Color(0xFFAFAFAF),
        fontFamily = FontFamily.Serif
    )

    OutlinedTextField(
        value = inputText,
        modifier = Modifier
            .padding(horizontal = 14.sdp)
            .padding(top = 10.sdp)
            .fillMaxWidth(),
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        placeholder = {
            Text(
                text = placeHolder,
                modifier = Modifier,
                fontSize = 12.ssp,
                color = Color(0xFF747474),
                fontFamily = FontFamily.Serif
            )
        },
        textStyle = TextStyle(
            fontSize = 12.ssp,
            color = Color.White,
            fontFamily = FontFamily.Serif
        ),
        shape = RoundedCornerShape(8.sdp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFF3E3D46),
            focusedBorderColor = Color(0xFF3E3D46),
            cursorColor = Color.White
        ),
    )
}

@Composable
fun ColumnScope.QRCodePreview(inputText: String, onNavigate: (String) -> Unit) {
    Text(
        text = "QR Code Preview",
        modifier = Modifier.padding(top = 24.sdp)
            .background(Color(0xFFE83242), RoundedCornerShape(topEnd = 50.sdp, bottomEnd = 50.sdp))
            .padding(vertical = 4.sdp)
            .padding(start = 14.sdp, end = 24.sdp),
        fontSize = 12.ssp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Serif
    )

    val painter = rememberQrKitPainter(inputText) {
        qrColors {
            darkColorBrush = QrKitBrush.customBrush {
                Brush.linearGradient(
                    0f to Color.White,
                    1f to Color.White,
                    end = Offset(it, it)
                )
            }
        }
    }

    Box(
        modifier = Modifier.padding(top = 20.sdp).align(Alignment.CenterHorizontally).size(170.sdp)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF252528), RoundedCornerShape(6.sdp))
                .clip(RoundedCornerShape(6.sdp))
                .align(Alignment.Center)
                .size(150.sdp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter, contentDescription = null, modifier = Modifier.size(130.sdp)
            )
        }

        Row(
            modifier = Modifier
                .background(Color(0xFF007AFF), RoundedCornerShape(50.sdp))
                .padding(vertical = 5.sdp, horizontal = 10.sdp)
                .align(Alignment.BottomCenter)
                .clickable {
                    onNavigate(
                        AppScreen.QRCustomization.route.replace(
                            "{${AppConstants.QR_TEXT}}", inputText
                        )
                    )
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(16.sdp),
                painter = painterResource(resource = Res.drawable.ic_magic_tool),
                contentScale = ContentScale.Fit,
                contentDescription = ""
            )

            Text(
                text = "Customize",
                modifier = Modifier.padding(start = 8.sdp),
                fontSize = 12.ssp,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontFamily = FontFamily.Serif
            )
        }
    }
}





