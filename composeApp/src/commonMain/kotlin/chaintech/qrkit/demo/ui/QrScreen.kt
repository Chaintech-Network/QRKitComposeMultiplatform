package chaintech.qrkit.demo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import qrkitcomposemultiplatform.composeapp.generated.resources.Res
import qrkitcomposemultiplatform.composeapp.generated.resources.img

class QrScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val datePickerList =
            listOf(
                "QrScannerCompose",
                "QrGeneratorCompose",
            )
        MaterialTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color(0xFF007AFF),
                            titleContentColor = Color.White,
                        ),
                        title = {
                            Text(
                                text = "QR Code",
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        }
                    )
                },
            ) {
                LazyColumn(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp),
                    contentPadding = it,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    content = {
                        items(count = datePickerList.size) { index ->
                            val category = datePickerList[index]
                            CategoryListItem(
                                category = category,
                                onClick = {
                                    goToComponentActivity(
                                        navigator = navigator,
                                        category = category
                                    )
                                }
                            )
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun CategoryListItem(
        category: String,
        onClick: () -> Unit
    ) {
        Card(
            modifier = Modifier
                .padding(vertical = 6.dp)
                .clickable {
                    onClick()
                },
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(modifier = Modifier) {
                Image(
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(resource = Res.drawable.img),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = ""
                )

                Text(
                    text = category,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

    private fun goToComponentActivity(
        navigator: Navigator,
        category: String
    ) {
        navigator.push(
            ComponentListScreen(
                category = category
            )
        )
    }
}

data class ComponentListScreen(
    val category: String
) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF007AFF),
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            IconButton(
                                onClick = {
                                    navigator.pop()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    tint = Color.White,
                                    contentDescription = "Back"
                                )
                            }
                            Text(
                                text = when (category) {
                                    "QrScannerCompose" -> "QR Scanner"
                                    "QrGeneratorCompose" -> "QR Generator"
                                    else -> ""
                                },
                                fontSize = 18.sp,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                )
            },
        ) {
            when (category) {
                "QrScannerCompose" -> QrScannerCompose()
                "QrGeneratorCompose" -> QrGeneratorCompose(it)
            }
        }
    }
}
