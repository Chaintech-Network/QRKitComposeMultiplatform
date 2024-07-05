package chaintech.qrkit.demo

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
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
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import chaintech.qrkit.demo.theme.AppTheme
import chaintech.qrkit.demo.ui.QrScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import qrkitcomposemultiplatform.composeapp.generated.resources.Res
import qrkitcomposemultiplatform.composeapp.generated.resources.ic_gallery_icon
import qrscanner.QrScanner

val LocalSnackBarHostState =
    compositionLocalOf<SnackbarHostState> { error("No SnackBarHostState provided") }

@Composable
fun App() {
    CompositionProvider { snackBarHostState ->
        Scaffold(
            snackbarHost = {
                SnackbarHost(snackBarHostState, Modifier.padding(bottom = 30.dp))
            }
        ) {
            Navigator(
                screen = QrScreen()
            ) { navigator ->
                Scaffold {
                    SlideTransition(navigator)
                }
            }
        }
    }
}

@Composable
fun CompositionProvider(content: @Composable (SnackbarHostState) -> Unit) {
    val snackBarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(
        LocalSnackBarHostState provides snackBarHostState,
    ) {
        content(snackBarHostState)
    }
}