package org.qrcodedemo.app.ui

import qrgenerator.barcodepainter.BarcodeFormat

data class BarCodeDataType(
    val title: String,
    val type: BarcodeFormat,
    var isSelected: Boolean = false
)
