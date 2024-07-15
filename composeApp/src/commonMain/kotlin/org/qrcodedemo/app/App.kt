package org.qrcodedemo.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.qrcodedemo.app.theme.AppTheme
import org.qrcodedemo.app.ui.QrScreen

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