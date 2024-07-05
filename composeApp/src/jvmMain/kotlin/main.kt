import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import chaintech.qrkit.demo.App
import java.awt.Dimension

fun main() = application {
    Window(
        title = "QRCodeDemo",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        onCloseRequest = ::exitApplication,
    ) {
        window.minimumSize = Dimension(150, 150)
        App()
    }
}