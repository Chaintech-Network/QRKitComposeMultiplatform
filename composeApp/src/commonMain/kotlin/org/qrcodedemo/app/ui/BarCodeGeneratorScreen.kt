package org.qrcodedemo.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.CalendarViewWeek
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.EmptyPath
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import qrcodedemo.composeapp.generated.resources.Aeonik_Bold
import qrcodedemo.composeapp.generated.resources.Aeonik_Regular
import qrcodedemo.composeapp.generated.resources.Res
import qrcodedemo.composeapp.generated.resources.bg
import qrcodedemo.composeapp.generated.resources.ic_done
import qrcodedemo.composeapp.generated.resources.ic_drop_down
import qrcodedemo.composeapp.generated.resources.img_qr_thumb
import qrcodedemo.composeapp.generated.resources.poppins_bold
import qrcodedemo.composeapp.generated.resources.poppins_medium
import qrcodedemo.composeapp.generated.resources.poppins_regular
import qrcodedemo.composeapp.generated.resources.poppins_semibold
import qrgenerator.barcodepainter.BarcodeFormat
import qrgenerator.barcodepainter.EmptyPainter
import qrgenerator.barcodepainter.rememberBarcodePainter

@Composable
fun BarCodeGeneratorScreen(onNavigate: (String) -> Unit) {
    var selectedTypeTitle by remember { mutableStateOf("ITF") }
    var selectedTypeFormat by remember { mutableStateOf(BarcodeFormat.ITF) }
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

    var inputText by remember { mutableStateOf("512345678901") }

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { contentPadding ->
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
                            text = "Bar Code",
                            modifier = Modifier
                                .padding(horizontal = 14.sdp)
                                .padding(top = 20.sdp),
                            style = TextStyle(
                                color = Color(0xFF4D8EFF),
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
                                color = Color(0xFF00FFA7),
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
                                onNavigate(AppConstants.BACK_CLICK_ROUTE)
                            },
                        tint = Color.White
                    )
                }

                Text(
                    text = "Choose Bar Code Type",
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

                OutlinedTextField(value = selectedTypeTitle,
                    modifier = Modifier
                        .padding(horizontal = 14.sdp)
                        .padding(top = 10.sdp)
                        .fillMaxWidth()
                        .clickable {
                            showSheet = true
                        },
                    onValueChange = {
                        selectedTypeTitle = it
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
                        Icon(
                            modifier = Modifier.padding(start = 10.sdp, end = 10.sdp).size(18.sdp),
                            imageVector = Icons.Filled.CalendarViewWeek,
                            tint = Color.White,
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

                BarCodeEditTextView("Please Enter Text", "Text", inputText, onValueChange = {
                    inputText = it
                })

                if (inputText.isEmpty().not()) {
                    BarCodePreview(
                        data = inputText,
                        type = selectedTypeFormat,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }

        if (showSheet) {
            BarCodeTypeBottomSheet(
                selectedIndex = selectedIndex,
                callBack = { index, data ->
                    showSheet = false
                    selectedIndex = index
                    selectedTypeTitle = data.title
                    selectedTypeFormat = data.type
                },
                onDismiss = { showSheet = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarCodeTypeBottomSheet(
    selectedIndex: Int,
    callBack: (Int, BarCodeDataType) -> Unit,
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
        val list = arrayListOf<BarCodeDataType>()
        list.add(BarCodeDataType("ITF", BarcodeFormat.ITF))
        list.add(BarCodeDataType("Codebar", BarcodeFormat.Codabar))
        list.add(BarCodeDataType("Code39", BarcodeFormat.Code39))
        list.add(BarCodeDataType("Code128", BarcodeFormat.Code128))
        list.add(BarCodeDataType("UPC E", BarcodeFormat.UPCE))
        list.add(BarCodeDataType("UPC A", BarcodeFormat.UPCA))
        list.add(BarCodeDataType("EAN 13", BarcodeFormat.EAN13))
        list.add(BarCodeDataType("EAN 8", BarcodeFormat.EAN8))
        list.add(BarCodeDataType("Code 93", BarcodeFormat.Code93))

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
                text = "Choose Bar Code Type",
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
                            Icon(
                                modifier = Modifier.size(18.sdp),
                                imageVector = Icons.Filled.CalendarViewWeek,
                                tint = Color.White,
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
fun BarCodeEditTextView(
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
fun ColumnScope.BarCodePreview(
    data: String,
    type: BarcodeFormat,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    val FontPoppins = FontFamily(
        Font(Res.font.poppins_regular, FontWeight.Normal),
        Font(Res.font.poppins_medium, FontWeight.Medium),
        Font(Res.font.poppins_semibold, FontWeight.SemiBold),
        Font(Res.font.poppins_bold, FontWeight.Bold)
    )

    Text(
        text = "Bar Code Preview",
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

    val barCodePainter = rememberBarcodePainter(data, type, brush = SolidColor(Color.White), onError = { throwable ->
        scope.launch {
            snackbarHostState.showSnackbar("Error occurred: ${throwable.message}")
        }
        // Return a fallback painter, for example, an empty painter
        EmptyPainter()
    })

    Box(
        modifier = Modifier
            .padding(top = 42.sdp, start = 20.sdp, end = 20.sdp)
            .background(
                color = Color(0xFF252528),
                shape = RoundedCornerShape(10.sdp)
            )
            .padding(horizontal = 20.sdp)
            .align(Alignment.CenterHorizontally)
    ) {
        Image(
            painter = barCodePainter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.sdp)
        )
    }
}




