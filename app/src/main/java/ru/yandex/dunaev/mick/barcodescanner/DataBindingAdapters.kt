package ru.yandex.dunaev.mick.barcodescanner

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("visibility")
fun View.SetVisibitily(b: Boolean){
    this.visibility = if(b) View.VISIBLE else View.GONE
}

@BindingAdapter("src")
fun ImageView.setBitmap(bitmap: Bitmap?){
    bitmap?: return
    Log.v("IMAGE","Set bitmap ${bitmap.width} x ${bitmap.height}")
    setImageBitmap(bitmap)
}

@BindingAdapter("adapter")
fun RecyclerView.recyclerAdapter(adp: CategoryAdapter<Any, ViewDataBinding>){
    adapter = adp
}

@BindingAdapter("barcodes")
fun RecyclerView.barcodes(list: List<Barcode>?){
    //adapter?: return
    (adapter as BarcodeAdapter).setItems(list)
}

@BindingAdapter("layout_manager")
fun RecyclerView.recyclerManager(manager: RecyclerView.LayoutManager){
    layoutManager = manager
}