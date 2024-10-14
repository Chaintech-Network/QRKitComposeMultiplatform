import androidx.compose.ui.window.ComposeUIViewController
import org.qrcodedemo.app.AppHome
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController(
    configure = {
        enforceStrictPlistSanityCheck = false
    }
) { AppHome() }
