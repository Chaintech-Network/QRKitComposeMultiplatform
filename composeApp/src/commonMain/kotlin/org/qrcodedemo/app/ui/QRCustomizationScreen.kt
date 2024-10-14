package org.qrcodedemo.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import qrcodedemo.composeapp.generated.resources.Res
import qrcodedemo.composeapp.generated.resources.ic_check_icon
import qrcodedemo.composeapp.generated.resources.ic_circle
import qrcodedemo.composeapp.generated.resources.ic_circle_eyes
import qrcodedemo.composeapp.generated.resources.ic_circle_pixel
import qrcodedemo.composeapp.generated.resources.ic_circle_round_eyes
import qrcodedemo.composeapp.generated.resources.ic_hexagon
import qrcodedemo.composeapp.generated.resources.ic_horizontal_line_pixel
import qrcodedemo.composeapp.generated.resources.ic_instagram
import qrcodedemo.composeapp.generated.resources.ic_linkedin
import qrcodedemo.composeapp.generated.resources.ic_pentagon
import qrcodedemo.composeapp.generated.resources.ic_pinterest
import qrcodedemo.composeapp.generated.resources.ic_round_corner_pixel
import qrcodedemo.composeapp.generated.resources.ic_rounded_circle_eyes
import qrcodedemo.composeapp.generated.resources.ic_rounded_eyes
import qrcodedemo.composeapp.generated.resources.ic_rounded_square_eyes
import qrcodedemo.composeapp.generated.resources.ic_square
import qrcodedemo.composeapp.generated.resources.ic_square_circle_eyes
import qrcodedemo.composeapp.generated.resources.ic_square_pixel
import qrcodedemo.composeapp.generated.resources.ic_square_round_eyes
import qrcodedemo.composeapp.generated.resources.ic_tiktok
import qrcodedemo.composeapp.generated.resources.ic_vertical_line_pixel
import qrcodedemo.composeapp.generated.resources.ic_youtube
import qrcodedemo.composeapp.generated.resources.sf_pro
import qrcodedemo.composeapp.generated.resources.sf_pro_bold
import qrcodedemo.composeapp.generated.resources.sf_pro_medium
import qrcodedemo.composeapp.generated.resources.sf_pro_semibold
import qrgenerator.qrkitpainter.PatternType
import qrgenerator.qrkitpainter.QrBallType
import qrgenerator.qrkitpainter.QrFrameType
import qrgenerator.qrkitpainter.QrKitBrush
import qrgenerator.qrkitpainter.QrKitColors
import qrgenerator.qrkitpainter.QrKitLogo
import qrgenerator.qrkitpainter.QrKitShapes
import qrgenerator.qrkitpainter.QrPixelType
import qrgenerator.qrkitpainter.customBrush
import qrgenerator.qrkitpainter.getSelectedFrameShape
import qrgenerator.qrkitpainter.getSelectedPattern
import qrgenerator.qrkitpainter.getSelectedPixel
import qrgenerator.qrkitpainter.getSelectedQrBall
import qrgenerator.qrkitpainter.rememberQrKitPainter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QRCustomizationView(inputText: String, onNavigate: (String) -> Unit) {
    val FontSfPro = FontFamily(
        Font(Res.font.sf_pro, FontWeight.Normal),
        Font(Res.font.sf_pro_medium, FontWeight.Medium),
        Font(Res.font.sf_pro_semibold, FontWeight.SemiBold),
        Font(Res.font.sf_pro_bold, FontWeight.Bold)
    )

    val pixelList = remember { getPixelList() }
    val eyesList = remember { eyesList() }
    val colorsList = remember { colorsList() }
    val logosList = remember { logosList() }
    val shapesList = remember { shapesList() }

    var selectedQrPixelType by remember { mutableStateOf<QrPixelType?>(null) }
    var selectedQRFrameType by remember { mutableStateOf<QrFrameType?>(null) }
    var selectedQRBallType by remember { mutableStateOf<QrBallType?>(null) }
    var selectedColorIndex by remember { mutableStateOf(-1) }
    var selectedLogoIndex by remember { mutableStateOf(-1) }
    var selectedShapeType by remember { mutableStateOf<PatternType?>(null) }

    val centerLogo =
        if (selectedLogoIndex != -1) painterResource(logosList[selectedLogoIndex].first) else null

    val color = if (selectedColorIndex != -1) colorsList[selectedColorIndex].first else listOf(
        Color.White,
        Color.White
    )

    val painter = rememberQrKitPainter(inputText) {
        shapes = QrKitShapes(
            ballShape = getSelectedQrBall(selectedQRBallType ?: QrBallType.SquareQrBall()),
            darkPixelShape = getSelectedPixel(selectedQrPixelType ?: QrPixelType.SquarePixel()),
            frameShape = getSelectedFrameShape(selectedQRFrameType ?: QrFrameType.SquareFrame()),
            codeShape = getSelectedPattern(selectedShapeType ?: PatternType.SquarePattern),
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
        logo = if (selectedLogoIndex != -1) QrKitLogo(centerLogo) else QrKitLogo()
    }


    Column(
        modifier = Modifier
            .background(Color(0xFF1D1C22))
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFF12101C),
                    RoundedCornerShape(bottomStart = 8.sdp, bottomEnd = 8.sdp)
                )
                .statusBarsPadding()
        ) {
            Row(
                modifier = Modifier.padding(top = 20.sdp, start = 14.sdp, end = 14.sdp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "QR Code Customization:",
                    modifier = Modifier.weight(1f),
                    fontSize = 14.ssp,
                    style = TextStyle(
                        color = Color(0xFFC17D10),
                        fontFamily = FontSfPro,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Icon(
                    Icons.Filled.Close,
                    "close",
                    modifier = Modifier.size(18.sdp).clickable {
                        onNavigate(AppConstants.BACK_CLICK_ROUTE)
                    },
                    tint = Color.White
                )
            }

            Box(
                modifier = Modifier.padding(top = 18.sdp, bottom = 18.sdp)
                    .background(Color(0xFF0D0D0D), RoundedCornerShape(6.sdp))
                    .clip(RoundedCornerShape(6.sdp))
                    .align(Alignment.CenterHorizontally)
                    .size(150.sdp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter, contentDescription = null, modifier = Modifier.size(130.sdp)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Choose Pattern",
                modifier = Modifier.padding(top = 16.sdp, start = 14.sdp, end = 14.sdp),
                fontSize = 12.ssp,
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontSfPro,
                    fontWeight = FontWeight.SemiBold
                )
            )

            LazyRow(
                modifier = Modifier
                    .padding(top = 12.sdp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 14.sdp, end = 8.sdp),
                horizontalArrangement = Arrangement.spacedBy(6.sdp),
            ) {
                items(pixelList.size) { i ->
                    Box(modifier = Modifier) {
                        Box(
                            modifier = if (pixelList[i].second == selectedQrPixelType) {
                                Modifier
                                    .padding(top = 4.sdp, end = 4.sdp)
                                    .background(Color(0xFF181818), RoundedCornerShape(5.sdp))
                                    .border(1.sdp, Color(0xFF007AFF), RoundedCornerShape(5.sdp))
                                    .clip(RoundedCornerShape(5.sdp))
                                    .clickable {
                                        selectedQrPixelType = pixelList[i].second
                                    }
                                    .size(50.sdp)
                            } else {
                                Modifier
                                    .padding(top = 4.sdp, end = 4.sdp)
                                    .background(Color(0xFF181818), RoundedCornerShape(5.sdp))
                                    .clip(RoundedCornerShape(5.sdp))
                                    .clickable {
                                        selectedQrPixelType = pixelList[i].second
                                    }
                                    .size(50.sdp)
                            }
                        ) {
                            Image(
                                painter = painterResource(pixelList[i].first),
                                contentDescription = null,
                                modifier = Modifier.size(40.sdp).align(Alignment.Center),
                                colorFilter = ColorFilter.tint(color = Color.White)
                            )
                        }
                        if (pixelList[i].second == selectedQrPixelType)
                            Image(
                                painter = painterResource(Res.drawable.ic_check_icon),
                                contentDescription = null,
                                modifier = Modifier.size(12.sdp).align(Alignment.TopEnd)
                                    .background(Color.White, CircleShape).clip(CircleShape)
                            )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(start = 14.sdp, end = 14.sdp, top = 14.sdp),
                thickness = 1.sdp,
                color = Color(0xFF25242B)
            )

            Text(
                text = "Choose Eyes",
                modifier = Modifier.padding(top = 16.sdp, start = 14.sdp, end = 14.sdp),
                fontSize = 12.ssp,
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontSfPro,
                    fontWeight = FontWeight.SemiBold
                )
            )

            LazyRow(
                modifier = Modifier.padding(top = 10.sdp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 14.sdp, end = 8.sdp),
                horizontalArrangement = Arrangement.spacedBy(12.sdp),
            ) {
                items(eyesList.size) { i ->
                    val data = eyesList[i]
                    Box(modifier = Modifier) {
                        Box(
                            modifier = if (data.second == selectedQRFrameType && data.third == selectedQRBallType) {
                                Modifier
                                    .padding(top = 4.sdp, end = 4.sdp)
                                    .background(Color(0xFF181818), RoundedCornerShape(5.sdp))
                                    .border(1.sdp, Color(0xFF007AFF), RoundedCornerShape(5.sdp))
                                    .clip(RoundedCornerShape(5.sdp))
                                    .clickable {
                                        selectedQRFrameType = data.second
                                        selectedQRBallType = data.third
                                    }
                                    .size(50.sdp)
                            } else {
                                Modifier
                                    .padding(top = 4.sdp, end = 4.sdp)
                                    .background(Color(0xFF181818), RoundedCornerShape(5.sdp))
                                    .clip(RoundedCornerShape(5.sdp))
                                    .clickable {
                                        selectedQRFrameType = data.second
                                        selectedQRBallType = data.third
                                    }
                                    .size(50.sdp)
                            }
                        ) {
                            Image(
                                painter = painterResource(data.first),
                                contentDescription = null,
                                modifier = Modifier.size(32.sdp).align(Alignment.Center),
                                colorFilter = ColorFilter.tint(color = Color.White)
                            )
                        }
                        if (data.second == selectedQRFrameType && data.third == selectedQRBallType) {
                            Image(
                                painter = painterResource(Res.drawable.ic_check_icon),
                                contentDescription = null,
                                modifier = Modifier.size(12.sdp)
                                    .align(Alignment.TopEnd)
                                    .background(Color.White, CircleShape)
                                    .clip(CircleShape)
                            )
                        }
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(start = 14.sdp, end = 14.sdp, top = 14.sdp),
                thickness = 1.sdp,
                color = Color(0xFF25242B)
            )

            Text(
                text = "Choose Color",
                modifier = Modifier.padding(top = 16.sdp, start = 14.sdp, end = 14.sdp),
                fontSize = 12.ssp,
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontSfPro,
                    fontWeight = FontWeight.SemiBold
                )
            )

            FlowRow(
                modifier = Modifier.padding(top = 16.sdp, start = 14.sdp, end = 12.sdp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(16.sdp),
                maxItemsInEachRow = 5,
            ) {
                repeat(colorsList.size) { i ->
                    val data = colorsList[i]
                    Box(modifier = Modifier) {
                        Box(
                            modifier = Modifier
                                .padding(end = 2.sdp)
                                .background(Color.White, CircleShape)
                                .clip(CircleShape)
                                .clickable {
                                    selectedColorIndex = i
                                }
                                .size(44.sdp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.sdp)
                                    .align(Alignment.Center)
                                    .clip(CircleShape)
                                    .background(
                                        brush = Brush.verticalGradient(data.first)
                                    )
                            )
                        }
                        if (i == selectedColorIndex) {
                            Image(
                                painter = painterResource(Res.drawable.ic_check_icon),
                                contentDescription = null,
                                modifier = Modifier.size(12.sdp).align(Alignment.TopEnd)
                                    .background(Color.White, CircleShape).clip(CircleShape)
                            )
                        }
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(start = 14.sdp, end = 14.sdp, top = 14.sdp),
                thickness = 1.sdp,
                color = Color(0xFF25242B)
            )

            Text(
                text = "Choose Center Logo",
                modifier = Modifier.padding(top = 16.sdp, start = 14.sdp, end = 14.sdp),
                fontSize = 12.ssp,
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontSfPro,
                    fontWeight = FontWeight.SemiBold
                )
            )

            FlowRow(
                modifier = Modifier.padding(top = 16.sdp, start = 14.sdp, end = 12.sdp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(16.sdp),
                maxItemsInEachRow = 5,
            ) {
                repeat(logosList.size) { i ->
                    val data = logosList[i]
                    Box(modifier = Modifier) {
                        Box(
                            modifier = Modifier
                                .padding(end = 2.sdp)
                                .background(Color.White, CircleShape)
                                .clip(CircleShape)
                                .clickable {
                                    selectedLogoIndex = i
                                }
                                .size(44.sdp)
                        ) {
                            Image(
                                painter = painterResource(data.first),
                                contentDescription = null,
                                modifier = Modifier.size(42.sdp).align(Alignment.Center)
                            )
                        }
                        if (i == selectedLogoIndex) {
                            Image(
                                painter = painterResource(Res.drawable.ic_check_icon),
                                contentDescription = null,
                                modifier = Modifier.size(12.sdp).align(Alignment.TopEnd)
                                    .background(Color.White, CircleShape).clip(CircleShape)
                            )
                        }
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(start = 14.sdp, end = 14.sdp, top = 14.sdp),
                thickness = 1.sdp,
                color = Color(0xFF25242B)
            )

            Text(
                text = "Choose Shape",
                modifier = Modifier.padding(top = 16.sdp, start = 14.sdp, end = 14.sdp),
                fontSize = 12.ssp,
                style = TextStyle(
                    color = Color.White,
                    fontFamily = FontSfPro,
                    fontWeight = FontWeight.SemiBold
                )
            )

            LazyRow(
                modifier = Modifier.padding(top = 10.sdp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(start = 14.sdp, end = 8.sdp),
                horizontalArrangement = Arrangement.spacedBy(12.sdp),
            ) {
                items(shapesList.size) { i ->
                    val data = shapesList[i]
                    Box(modifier = Modifier) {
                        Box(
                            modifier = if (data.second == selectedShapeType) {
                                Modifier
                                    .padding(top = 4.sdp, end = 4.sdp)
                                    .background(Color(0xFF181818), RoundedCornerShape(5.sdp))
                                    .border(1.sdp, Color(0xFF007AFF), RoundedCornerShape(5.sdp))
                                    .clip(RoundedCornerShape(5.sdp))
                                    .clickable {
                                        selectedShapeType = data.second
                                    }
                                    .size(50.sdp)
                            } else {
                                Modifier
                                    .padding(top = 4.sdp, end = 4.sdp)
                                    .background(Color(0xFF181818), RoundedCornerShape(5.sdp))
                                    .clip(RoundedCornerShape(5.sdp))
                                    .clickable {
                                        selectedShapeType = data.second
                                    }
                                    .size(50.sdp)
                            }
                        ) {
                            Image(
                                painter = painterResource(data.first),
                                contentDescription = null,
                                modifier = Modifier.size(40.sdp).align(Alignment.Center)
                            )
                        }
                        if (data.second == selectedShapeType) {
                            Image(
                                painter = painterResource(Res.drawable.ic_check_icon),
                                contentDescription = null,
                                modifier = Modifier.size(12.sdp).align(Alignment.TopEnd)
                                    .background(Color.White, CircleShape).clip(CircleShape)
                            )
                        }
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(start = 14.sdp, end = 14.sdp, top = 14.sdp),
                thickness = 1.sdp,
                color = Color(0xFF25242B)
            )

            Spacer(modifier = Modifier.height(50.sdp).fillMaxWidth())
        }
    }
}

fun getPixelList(): List<Pair<DrawableResource, QrPixelType>> {
    return listOf(
        Pair(Res.drawable.ic_vertical_line_pixel, QrPixelType.VerticalLinePixel()),
        Pair(Res.drawable.ic_horizontal_line_pixel, QrPixelType.HorizontalLinePixel()),
        Pair(Res.drawable.ic_round_corner_pixel, QrPixelType.RoundCornerPixel()),
        Pair(Res.drawable.ic_circle_pixel, QrPixelType.CirclePixel()),
        Pair(Res.drawable.ic_square_pixel, QrPixelType.SquarePixel()),
    )
}

fun eyesList(): List<Triple<DrawableResource, QrFrameType, QrBallType>> {
    return listOf(
        Triple(
            Res.drawable.ic_square_round_eyes,
            QrFrameType.SquareFrame(),
            QrBallType.RoundCornersQrBall(0.25f)
        ),
        Triple(
            Res.drawable.ic_square_circle_eyes,
            QrFrameType.SquareFrame(),
            QrBallType.CircleQrBall()
        ),
        Triple(
            Res.drawable.ic_circle_round_eyes,
            QrFrameType.CircleFrame(),
            QrBallType.RoundCornersQrBall(0.25f)
        ),
        Triple(Res.drawable.ic_circle_eyes, QrFrameType.CircleFrame(), QrBallType.CircleQrBall()),
        Triple(
            Res.drawable.ic_rounded_square_eyes,
            QrFrameType.RoundCornersFrame(0.25f),
            QrBallType.SquareQrBall()
        ),
        Triple(
            Res.drawable.ic_rounded_eyes,
            QrFrameType.RoundCornersFrame(0.25f),
            QrBallType.RoundCornersQrBall(0.25f)
        ),
        Triple(
            Res.drawable.ic_rounded_circle_eyes,
            QrFrameType.RoundCornersFrame(0.25f),
            QrBallType.CircleQrBall()
        ),
    )
}

fun colorsList(): List<Pair<List<Color>, String>> {
    return listOf(
        Pair(listOf(Color(0xFF09A1BD), Color(0xFF09A1BD)), "COLOR_01"),
        Pair(listOf(Color(0xFF86BD41), Color(0xFF86BD41)), "COLOR_02"),
        Pair(listOf(Color(0xFFEF831E), Color(0xFFEF831E)), "COLOR_03"),
        Pair(listOf(Color(0xFFFF3849), Color(0xFFFF3849)), "COLOR_04"),
        Pair(listOf(Color(0xFF8B37F7), Color(0xFF8B37F7)), "COLOR_05"),
        Pair(listOf(Color(0xFFEF831E), Color(0xFF09A1BD)), "COLOR_06"),
        Pair(listOf(Color(0xFF8B37F7), Color(0xFFEF831E)), "COLOR_07"),
        Pair(listOf(Color(0xFFFF3849), Color(0xFFEF831E)), "COLOR_08"),
        Pair(listOf(Color(0xFF8B37F7), Color(0xFFFF3849)), "COLOR_09"),
        Pair(listOf(Color(0xFFFF3849), Color(0xFF09A1BD)), "COLOR_10"),
    )
}

fun logosList(): List<Pair<DrawableResource, String>> {
    return listOf(
        Pair(Res.drawable.ic_linkedin, "LINKEDIN"),
        Pair(Res.drawable.ic_tiktok, "TIKTOK"),
        Pair(Res.drawable.ic_youtube, "YOUTUBE"),
        Pair(Res.drawable.ic_pinterest, "PINTEREST"),
        Pair(Res.drawable.ic_instagram, "INSTAGRAM"),
    )
}

fun shapesList(): List<Pair<DrawableResource, PatternType>> {
    return listOf(
        Pair(Res.drawable.ic_circle, PatternType.CirclePattern()),
        Pair(Res.drawable.ic_hexagon, PatternType.HexagonPattern()),
        Pair(Res.drawable.ic_pentagon, PatternType.PentagonPattern()),
        Pair(Res.drawable.ic_square, PatternType.SquarePattern),
    )
}

