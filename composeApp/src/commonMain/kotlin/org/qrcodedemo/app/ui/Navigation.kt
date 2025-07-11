package org.qrcodedemo.app.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object AppConstants {
    const val BACK_CLICK_ROUTE = "BACK_CLICK_ROUTE"
    const val NAV_QR_GENERATOR = "nav_qr_generator"
    const val NAV_QR_CUSTOMISATION = "nav_qr_customization"
    const val NAV_QR_SCANNER = "nav_qr_scanner"
    const val NAV_BARCODE_GENERATOR = "nav_qr_barcode_generator"
}

data class NavigationData(
    val routeName: String,
    val data: String = ""
)

@Serializable
object QRKitMainScreenRoute

@Serializable
object QRGeneratorRoute

@Serializable
data class QRCustomizationRoute(val qrText: String)

@Serializable
object QRScannerRoute

@Serializable
object BarCodeGeneratorRoute


@Composable
fun QRKitNav() {
    val navController = rememberNavController()

    NavHostMain(
        navController = navController,
        onNavigate = { routeName ->
            navigateTo(routeName, navController)
        }
    )
}

@Composable
fun NavHostMain(
    navController: NavHostController = rememberNavController(),
    onNavigate: (navigationData: NavigationData) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = QRKitMainScreenRoute,
        modifier = Modifier
            .fillMaxSize(),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        }
    ) {
        composable<QRKitMainScreenRoute> {
            QRKitMainScreen(onNavigate)
        }

        composable<QRGeneratorRoute> {
            QrGeneratorView(onNavigate)
        }

        composable<QRCustomizationRoute> { backStackEntry ->
            val args = backStackEntry.toRoute<QRCustomizationRoute>()
            QRCustomizationView(args.qrText, onNavigate)
        }

        composable<QRScannerRoute> {
            QrScannerView(onNavigate = onNavigate)
        }

        composable<BarCodeGeneratorRoute> {
            BarCodeGeneratorScreen(onNavigate = onNavigate)
        }
    }
}

fun navigateTo(
    navigationData: NavigationData,
    navController: NavController
) {
    when (navigationData.routeName) {
        AppConstants.BACK_CLICK_ROUTE -> {
            navController.popBackStack()
        }

        AppConstants.NAV_QR_GENERATOR -> {
            navController.navigate(QRGeneratorRoute)
        }

        AppConstants.NAV_QR_CUSTOMISATION -> {
            navController.navigate(QRCustomizationRoute(qrText = navigationData.data))
        }

        AppConstants.NAV_QR_SCANNER -> {
            navController.navigate(QRScannerRoute)
        }

        AppConstants.NAV_BARCODE_GENERATOR -> {
            navController.navigate(BarCodeGeneratorRoute)
        }

        else -> {
            navController.navigate(navigationData.routeName)
        }
    }
}