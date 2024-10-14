package org.qrcodedemo.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import qrcodedemo.composeapp.generated.resources.Res
import qrcodedemo.composeapp.generated.resources.SofiaSansCondensed_Bold
import qrcodedemo.composeapp.generated.resources.SofiaSansCondensed_Light
import qrcodedemo.composeapp.generated.resources.SofiaSansCondensed_Medium
import qrcodedemo.composeapp.generated.resources.SofiaSansCondensed_Regular
import qrcodedemo.composeapp.generated.resources.SofiaSansCondensed_SemiBold
import qrcodedemo.composeapp.generated.resources.ic_arrow_row
import qrcodedemo.composeapp.generated.resources.img_barcode
import qrcodedemo.composeapp.generated.resources.img_curved_arrow
import qrcodedemo.composeapp.generated.resources.img_dotted_box
import qrcodedemo.composeapp.generated.resources.img_mobile_innovation
import qrcodedemo.composeapp.generated.resources.img_qr_lines
import qrcodedemo.composeapp.generated.resources.img_qr_thumb
import qrcodedemo.composeapp.generated.resources.img_scanner

@Composable
fun QRKitMainScreen(onNavigate: (String) -> Unit) {
    val FontSofia = FontFamily(
        Font(Res.font.SofiaSansCondensed_Light, FontWeight.Light),
        Font(Res.font.SofiaSansCondensed_Regular, FontWeight.Normal),
        Font(Res.font.SofiaSansCondensed_Medium, FontWeight.Medium),
        Font(Res.font.SofiaSansCondensed_SemiBold, FontWeight.SemiBold),
        Font(Res.font.SofiaSansCondensed_Bold, FontWeight.Bold)
    )

    MaterialTheme {
        Column(
            modifier = Modifier
                .background(color = Color(0xFF1D1C22))
                .statusBarsPadding()
                .fillMaxSize()
                .padding(16.sdp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFF6265DE),
                        shape = RoundedCornerShape(topStart = 22.sdp, topEnd = 22.sdp, bottomStart = 22.sdp, bottomEnd = 48.sdp)
                    )
            ) {
                Image(
                    modifier = Modifier
                        .size(90.sdp)
                        .align(Alignment.TopEnd)
                        .padding(end = 4.sdp, top = 4.sdp),
                    painter = painterResource(resource = Res.drawable.img_mobile_innovation),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = ""
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 16.sdp, top = 12.sdp, bottom = 26.sdp)
                ) {
                    Image(
                        painter = painterResource(resource = Res.drawable.ic_arrow_row),
                        contentScale = ContentScale.Fit,
                        contentDescription = ""
                    )

                    Text(
                        text = "QRKit",
                        modifier = Modifier
                            .padding(top = 22.sdp),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontSofia,
                            fontWeight = FontWeight.Bold
                        ),
                        fontSize = 45.ssp
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.sdp, end = 16.sdp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Compose\nMultiplatform",
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = FontSofia,
                                fontWeight = FontWeight.Normal
                            ),
                            fontSize = 30.ssp
                        )

                        Image(
                            modifier = Modifier
                                .size(40.sdp),
                            painter = painterResource(resource = Res.drawable.img_dotted_box),
                            contentScale = ContentScale.Fit,
                            contentDescription = ""
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(top = 20.sdp),
                horizontalArrangement = Arrangement.spacedBy(12.sdp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .fillMaxHeight()
                        .background(
                            color = Color(0xFF0B2447),
                            shape = RoundedCornerShape(topStart = 20.sdp, topEnd = 60.sdp, bottomStart = 20.sdp, bottomEnd = 60.sdp)
                        )
                        .clickable {
                            onNavigate(AppScreen.QRGenerator.route)
                        }
                ) {
                    Image(
                        modifier = Modifier
                            .padding(top = 2.sdp, end = 20.sdp)
                            .align(Alignment.TopEnd),
                        painter = painterResource(resource = Res.drawable.img_qr_lines),
                        contentDescription = ""
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 16.sdp, top = 12.sdp)
                    ) {
                        Text(
                            text = "QR Code",
                            modifier = Modifier,
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = FontSofia,
                                fontWeight = FontWeight.Bold
                            ),
                            fontSize = 16.ssp
                        )

                        Text(
                            text = "Generator",
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = FontSofia,
                                fontWeight = FontWeight.Normal
                            ),
                            fontSize = 25.ssp
                        )

                        Image(
                            modifier = Modifier
                                .padding(top = 8.sdp)
                                .width(52.sdp)
                                .height(32.sdp),
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(resource = Res.drawable.img_qr_thumb),
                            contentDescription = ""
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .background(
                            color = Color(0xFF327D6A),
                            shape = RoundedCornerShape(18.sdp)
                        )
                        .padding(horizontal = 16.sdp, vertical = 12.sdp)
                        .clickable {
                            onNavigate(AppScreen.QRScanner.route)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .width(50.sdp)
                            .height(40.sdp),
                        painter = painterResource(resource = Res.drawable.img_scanner),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = ""
                    )

                    Text(
                        text = "QR & Barcode",
                        modifier = Modifier
                            .padding(top = 12.sdp),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontSofia,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        fontSize = 14.ssp
                    )

                    Text(
                        text = "Scanner",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontSofia,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        ),
                        fontSize = 22.ssp
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.sdp)
                    .background(
                        color = Color(0xFF1C3754),
                        shape = RoundedCornerShape(18.sdp)
                    )
                    .clickable {
                        onNavigate(AppScreen.BarCodeGenerator.route)
                    }
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 16.sdp, top = 12.sdp)
                ) {
                    Text(
                        text = "Bar Code",
                        modifier = Modifier,
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontSofia,
                            fontWeight = FontWeight.Bold
                        ),
                        fontSize = 16.ssp
                    )

                    Text(
                        text = "Generator",
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontSofia,
                            fontWeight = FontWeight.Normal
                        ),
                        fontSize = 25.ssp
                    )
                }

                Image(
                    modifier = Modifier
                        .align(Alignment.Center),
                    painter = painterResource(resource = Res.drawable.img_curved_arrow),
                    contentDescription = ""
                )

                Image(
                    modifier = Modifier
                        .padding(end = 16.sdp, top = 45.sdp, bottom = 16.sdp)
                        .width(80.sdp)
                        .height(40.sdp)
                        .align(Alignment.BottomEnd),
                    painter = painterResource(resource = Res.drawable.img_barcode),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = ""
                )
            }
        }
    }
}



