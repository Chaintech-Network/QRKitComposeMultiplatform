package org.qrcodedemo.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Switch
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import qrcodedemo.composeapp.generated.resources.Aeonik_Bold
import qrcodedemo.composeapp.generated.resources.Aeonik_Regular
import qrcodedemo.composeapp.generated.resources.Res
import qrcodedemo.composeapp.generated.resources.bg
import qrcodedemo.composeapp.generated.resources.ic_calendar
import qrcodedemo.composeapp.generated.resources.ic_done
import qrcodedemo.composeapp.generated.resources.ic_drop_down
import qrcodedemo.composeapp.generated.resources.ic_email
import qrcodedemo.composeapp.generated.resources.ic_magic_tool
import qrcodedemo.composeapp.generated.resources.ic_phone
import qrcodedemo.composeapp.generated.resources.ic_sms
import qrcodedemo.composeapp.generated.resources.ic_text
import qrcodedemo.composeapp.generated.resources.ic_text_blue
import qrcodedemo.composeapp.generated.resources.ic_website
import qrcodedemo.composeapp.generated.resources.ic_wifi
import qrcodedemo.composeapp.generated.resources.poppins_bold
import qrcodedemo.composeapp.generated.resources.poppins_medium
import qrcodedemo.composeapp.generated.resources.poppins_regular
import qrcodedemo.composeapp.generated.resources.poppins_semibold
import qrgenerator.QRCodeImage
import qrgenerator.qrkitpainter.QrKitBrush
import qrgenerator.qrkitpainter.QrKitColors
import qrgenerator.qrkitpainter.customBrush
import qrgenerator.qrkitpainter.rememberQrKitPainter
import qrgenerator.shareQrCodeImage

@Composable
fun QrGeneratorView(onNavigate: (NavigationData) -> Unit) {
    var selectedType by remember { mutableStateOf("Text") }
    var selectedIndex by remember { mutableStateOf(0) }
    var showSheet by remember { mutableStateOf(false) }

    val FontAeonik = FontFamily(
        Font(Res.font.Aeonik_Regular, FontWeight.Normal),
        Font(Res.font.Aeonik_Bold, FontWeight.Bold)
    )

    val FontPoppins = FontFamily(
        Font(Res.font.poppins_regular, FontWeight.Normal),
        Font(Res.font.poppins_medium, FontWeight.Medium),
        Font(Res.font.poppins_semibold, FontWeight.SemiBold),
        Font(Res.font.poppins_bold, FontWeight.Bold)
    )

    Box(
        modifier = Modifier
            .background(Color(0xFF1D1C22))
            .statusBarsPadding()
            .fillMaxSize()
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.sdp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "QR Code",
                        modifier = Modifier
                            .padding(horizontal = 14.sdp)
                            .padding(top = 20.sdp),
                        style = TextStyle(
                            color = Color(0xFFFD66B5),
                            fontFamily = FontAeonik,
                            fontWeight = FontWeight.Bold
                        ),
                        fontSize = 14.ssp
                    )

                    Text(
                        text = "Generator",
                        modifier = Modifier
                            .padding(horizontal = 14.sdp)
                            .padding(top = 2.sdp),
                        fontSize = 26.ssp,
                        style = TextStyle(
                            color = Color(0xFFFF8066),
                            fontFamily = FontAeonik,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Icon(
                    Icons.Filled.Close,
                    "close",
                    modifier = Modifier
                        .size(28.sdp)
                        .clickable {
                            onNavigate(NavigationData(AppConstants.BACK_CLICK_ROUTE))
                        },
                    tint = Color.White
                )
            }

            Text(
                text = "Choose QR Code type",
                modifier = Modifier
                    .padding(horizontal = 14.sdp)
                    .padding(top = 22.sdp),
                fontSize = 10.ssp,
                style = TextStyle(
                    color = Color(0xFFAFAFAF),
                    fontFamily = FontPoppins,
                    fontWeight = FontWeight.Medium
                )
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
                        style = TextStyle(
                            color = Color(0xFF747474),
                            fontFamily = FontPoppins,
                            fontWeight = FontWeight.Medium
                        )
                    )
                },
                textStyle = TextStyle(
                    fontSize = 12.ssp, color = Color.White, fontFamily = FontPoppins, fontWeight = FontWeight.Medium
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
    val FontPoppins = FontFamily(
        Font(Res.font.poppins_regular, FontWeight.Normal),
        Font(Res.font.poppins_medium, FontWeight.Medium),
        Font(Res.font.poppins_semibold, FontWeight.SemiBold),
        Font(Res.font.poppins_bold, FontWeight.Bold)
    )

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
                style = TextStyle(
                    color = Color(0xFF7A7AD9),
                    fontFamily = FontPoppins,
                    fontWeight = FontWeight.SemiBold
                )
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
                                style = TextStyle(
                                    color = Color(0xFFAFAFAF),
                                    fontFamily = FontPoppins,
                                    fontWeight = FontWeight.Medium
                                )
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
    val FontPoppins = FontFamily(
        Font(Res.font.poppins_regular, FontWeight.Normal),
        Font(Res.font.poppins_medium, FontWeight.Medium),
        Font(Res.font.poppins_semibold, FontWeight.SemiBold),
        Font(Res.font.poppins_bold, FontWeight.Bold)
    )

    Text(
        text = labelText,
        modifier = Modifier.padding(horizontal = 14.sdp).padding(top = 20.sdp),
        fontSize = 10.ssp,
        style = TextStyle(
            color = Color(0xFFAFAFAF),
            fontFamily = FontPoppins,
            fontWeight = FontWeight.Medium
        )
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
            fontFamily = FontPoppins,
            fontWeight = FontWeight.Medium
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
fun ColumnScope.QRCodePreview(inputText: String, onNavigate: (NavigationData) -> Unit) {
    val FontPoppins = FontFamily(
        Font(Res.font.poppins_regular, FontWeight.Normal),
        Font(Res.font.poppins_medium, FontWeight.Medium),
        Font(Res.font.poppins_semibold, FontWeight.SemiBold),
        Font(Res.font.poppins_bold, FontWeight.Bold)
    )

    Text(
        text = "QR Code Preview",
        modifier = Modifier.padding(top = 24.sdp)
            .background(Color(0xFF006588), RoundedCornerShape(topEnd = 50.sdp, bottomEnd = 50.sdp))
            .padding(vertical = 4.sdp)
            .padding(start = 14.sdp, end = 24.sdp),
        style = TextStyle(
            color = Color.White,
            fontFamily = FontPoppins,
            fontWeight = FontWeight.SemiBold
        ),
        fontSize = 12.ssp
    )

    var showSharable by remember { mutableStateOf(false) }

    /** Painter for displaying QR Code */
    val painter = rememberQrKitPainter(inputText) {
        colors = QrKitColors(
            darkBrush = QrKitBrush.customBrush { SolidColor(Color.White) }
        )
    }

    var qrImageBitmap: ImageBitmap? = null

    Column(
        modifier = Modifier
            .padding(top = 20.sdp)
            .align(Alignment.CenterHorizontally)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Show sharable QR code", color = Color.White)
            Spacer(modifier = Modifier.width(8.sdp))
            Switch(
                checked = showSharable,
                onCheckedChange = {
                    showSharable = it
                }
            )
        }

        Box(
            modifier = Modifier
                .size(170.sdp)
                .padding(top = 10.sdp)
        ) {
            Box(
                modifier = Modifier
                    .background(Color(0xFF252528), RoundedCornerShape(6.sdp))
                    .clip(RoundedCornerShape(6.sdp))
                    .align(Alignment.Center)
                    .size(150.sdp),
                contentAlignment = Alignment.Center
            ) {
                if (showSharable) {
                    /** Use this if you just want to display a Sharable QR Code */
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
                } else {
                    /** Use this if you just want to display the QR Code */
                    Image(
                        painter = painter, contentDescription = null, modifier = Modifier.size(130.sdp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .background(Color(0xFF007AFF), RoundedCornerShape(50.sdp))
                    .padding(top = 5.sdp, bottom = 5.sdp, start = 10.sdp, end = 12.sdp)
                    .align(Alignment.BottomCenter)
                    .clickable {
                        onNavigate(
                            NavigationData(
                                routeName = AppConstants.NAV_QR_CUSTOMISATION,
                                data = inputText
                            )
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(14.sdp),
                    painter = painterResource(resource = Res.drawable.ic_magic_tool),
                    contentScale = ContentScale.Fit,
                    contentDescription = ""
                )

                Text(
                    text = "Customize",
                    modifier = Modifier.padding(start = 6.sdp),
                    fontSize = 10.ssp,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontPoppins,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        if (showSharable) {
            Row(
                modifier = Modifier
                    .padding(top = 14.sdp, bottom = 5.sdp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        qrImageBitmap?.let {
                            shareQrCodeImage(qrImageBitmap, "QR Code")
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Default.Share,
                    modifier = Modifier.size(16.sdp),
                    tint = Color.White,
                    contentDescription = ""
                )

                Text(
                    text = "Share QR Code",
                    modifier = Modifier.padding(start = 6.sdp),
                    fontSize = 12.ssp,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontPoppins,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}




