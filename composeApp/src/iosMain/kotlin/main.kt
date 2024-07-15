import androidx.compose.ui.window.ComposeUIViewController
import org.qrcodedemo.app.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
