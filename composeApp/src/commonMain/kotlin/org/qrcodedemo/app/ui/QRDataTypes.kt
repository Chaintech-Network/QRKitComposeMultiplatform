package org.qrcodedemo.app.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.DrawableResource
import qrgenerator.qrkitpainter.email
import qrgenerator.qrkitpainter.event
import qrgenerator.qrkitpainter.phone
import qrgenerator.qrkitpainter.sms
import qrgenerator.qrkitpainter.wifi

data class QRType(val icon: DrawableResource, val title: String, var isSelected: Boolean = false)

@Composable
fun ColumnScope.TextQR(onNavigate: (String) -> Unit) {
    var inputText by remember { mutableStateOf("Lorem Ipsum is simply dummy text of the printing and typesetting industry.") }

    QREditTextView("Please Enter Text", "Text", inputText, onValueChange = {
        inputText = it
    })

    if (inputText.isEmpty().not()) {
        QRCodePreview(inputText, onNavigate)
    }
}

@Composable
fun ColumnScope.WebsiteQR(onNavigate: (String) -> Unit) {
    var websiteText by rememberSaveable { mutableStateOf("https://github.com/Chaintech-Network/QRKitComposeMultiplatform.git") }

    QREditTextView("Please Enter Website", "Website", websiteText, onValueChange = {
        websiteText = it
    })

    if (websiteText.isEmpty().not()) {
        QRCodePreview(websiteText, onNavigate)
    }
}

@Composable
fun ColumnScope.EmailQR(onNavigate: (String) -> Unit) {
    var emailText by rememberSaveable { mutableStateOf("developer@gmail.com") }
    var copyToText by rememberSaveable { mutableStateOf("addeveloper@gmail.com") }
    var subjectText by rememberSaveable { mutableStateOf("Lorem ipsum") }
    var bodyText by rememberSaveable { mutableStateOf("Lorem ipsum") }

    QREditTextView("Please Enter Email", "Email", emailText, onValueChange = {
        emailText = it
    })

    QREditTextView("Please Enter Copy To", "Copy To", copyToText, onValueChange = {
        copyToText = it
    })

    QREditTextView("Please Enter Subject", "Subject", subjectText, onValueChange = {
        subjectText = it
    })

    QREditTextView("Please Enter Body", "Body", bodyText, onValueChange = {
        bodyText = it
    })

    if (emailText.isNotEmpty()) {
        QRCodePreview(email(emailText, copyToText, subjectText, bodyText), onNavigate)
    }
}

@Composable
fun ColumnScope.SMSQR(onNavigate: (String) -> Unit) {
    var phoneNumberText by rememberSaveable { mutableStateOf("7665565456") }
    var subjectText by rememberSaveable { mutableStateOf("Lorem ipsum") }

    QREditTextView("Please Enter Phone Number", "Phone Number", phoneNumberText, onValueChange = {
        phoneNumberText = it
    })

    QREditTextView("Please Enter Subject", "Subject", subjectText, onValueChange = {
        subjectText = it
    })

    if (phoneNumberText.isNotEmpty() && subjectText.isNotEmpty()) {
        QRCodePreview(sms(phoneNumberText, subjectText, false), onNavigate)
    }
}

@Composable
fun ColumnScope.WifiQR(onNavigate: (String) -> Unit) {
    var authenticationText by rememberSaveable { mutableStateOf("Android@123") }
    var ssidText by rememberSaveable { mutableStateOf("Android Framework") }
    var pskText by rememberSaveable { mutableStateOf("") }

    QREditTextView(
        "Please Enter Authentication",
        "Authentication",
        authenticationText,
        onValueChange = {
            authenticationText = it
        })

    QREditTextView("Please Enter SSID", "SSID", ssidText, onValueChange = {
        ssidText = it
    })

    QREditTextView("Please Enter PSK", "PSK", pskText, onValueChange = {
        pskText = it
    })

    QRCodePreview(wifi(authenticationText, ssidText, pskText), onNavigate)
}

@Composable
fun ColumnScope.PhoneQR(onNavigate: (String) -> Unit) {
    var phoneNumberText by rememberSaveable { mutableStateOf("7889965675") }

    QREditTextView(
        "Please Enter Phone Number",
        "Phone Number",
        phoneNumberText,
        onValueChange = {
            phoneNumberText = it
        })

    if (phoneNumberText.isNotEmpty()) {
        QRCodePreview(phone(phoneNumberText), onNavigate)
    }
}

@Composable
fun ColumnScope.CalendarQR(onNavigate: (String) -> Unit) {
    var uidText by rememberSaveable { mutableStateOf("54543") }
    var stampText by rememberSaveable { mutableStateOf("STORE") }
    var organizerText by rememberSaveable { mutableStateOf("Angry Film Studio") }
    var startText by rememberSaveable { mutableStateOf("24/08/2024") }
    var endText by rememberSaveable { mutableStateOf("30/08/2024") }
    var summaryText by rememberSaveable { mutableStateOf("Lorem ipsum") }

    QREditTextView(
        "Please Enter uid",
        "uid",
        uidText,
        onValueChange = {
            uidText = it
        })

    QREditTextView(
        "Please Enter Stamp",
        "Stamp",
        stampText,
        onValueChange = {
            stampText = it
        })

    QREditTextView(
        "Please Enter Organizer",
        "Organizer",
        organizerText,
        onValueChange = {
            organizerText = it
        })

    QREditTextView(
        "Please Enter Start",
        "Start",
        startText,
        onValueChange = {
            startText = it
        })

    QREditTextView(
        "Please Enter End",
        "End",
        endText,
        onValueChange = {
            endText = it
        })

    QREditTextView(
        "Please Enter Summary",
        "Summary",
        summaryText,
        onValueChange = {
            summaryText = it
        })

    QRCodePreview(event(uidText, stampText, organizerText, startText, endText, summaryText), onNavigate)
}

