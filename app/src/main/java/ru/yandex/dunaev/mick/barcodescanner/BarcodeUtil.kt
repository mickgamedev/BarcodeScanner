package ru.yandex.dunaev.mick.barcodescanner

import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode.*

fun barcodeFormatToString(format:Int): String{
    var result = ""
    when(format){
        FORMAT_UNKNOWN -> result = "Unknown"
        FORMAT_AZTEC -> result = "Aztec"
        FORMAT_CODABAR -> result = "Codabar"
        FORMAT_CODE_128 -> result = "Code 128"
        FORMAT_CODE_39 -> result = "Code 39"
        FORMAT_CODE_93 -> result = "Code 93"
        FORMAT_DATA_MATRIX -> result = "Data matrix"
        FORMAT_EAN_13 -> result = "EAN 13"
        FORMAT_EAN_8 -> result = "EAN 8"
        FORMAT_ITF -> result = "ITF"
        FORMAT_PDF417 -> result = "PDF 417"
        FORMAT_QR_CODE -> result = "QR Code"
        FORMAT_UPC_A -> result = "UPC A"
        FORMAT_UPC_E -> result = "UPC E"
    }
    return result
}

class Barcode(bc: FirebaseVisionBarcode){
    val displayValue = bc.displayValue?: ""
    val format = barcodeFormatToString(bc.format)
    val formatInt = bc.format
}