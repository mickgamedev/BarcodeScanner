package ru.yandex.dunaev.mick.barcodescanner

import android.graphics.*
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.common.FirebaseVisionImage

class MainViewModel: ViewModel() {
    val image = ObservableField<Bitmap>()
    val progress = ObservableBoolean(false)
    val isBarcode = ObservableBoolean(false)

    val adapter = ObservableField<BarcodeAdapter>(BarcodeAdapter())
    val list = ObservableField<List<Barcode>>()

    fun setImage(bitmap: Bitmap?) {
        isBarcode.set(false)
        image.set(bitmap)
        barcodeDetector()
    }

    private fun barcodeDetector(){
        val bitmap = image.get()?: return
        progress.set(true)
        val detector = FirebaseVision.getInstance().visionBarcodeDetector
        detector.detectInImage(FirebaseVisionImage.fromBitmap(bitmap))
            .addOnCompleteListener {
                showBarcodes(it.result)
                progress.set(false)
            }
    }

    private fun showBarcodes(result: List<FirebaseVisionBarcode>?) {
        if(result == null || result.size == 0) {
            isBarcode.set(true)
            return
        }
        val markedBitmap = image.get()?.copy(Bitmap.Config.ARGB_8888, true)
        markedBitmap?: return
        val listBarcodes = mutableListOf<Barcode>()
        val canvas = Canvas(markedBitmap)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.parseColor("#99003399") }
        result.forEach {
            val boundingBox = it.boundingBox
            if(boundingBox != null) canvas.drawRect(boundingBox, paint)
            listBarcodes.add(Barcode(it))
        }
        image.set(markedBitmap)
        list.set(listBarcodes.toList())
    }
}

