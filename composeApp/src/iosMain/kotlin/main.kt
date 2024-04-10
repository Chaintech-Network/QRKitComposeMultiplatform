import androidx.compose.ui.window.ComposeUIViewController
import chaintech.qrkit.demo.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
