package ru.yandex.dunaev.mick.barcodescanner

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.dunaev.mick.barcodescanner.databinding.CardSimpleBinding

class BarcodeAdapter : CategoryAdapter<Barcode, CardSimpleBinding>(R.layout.card_simple) {
    override fun onBindViewHolder(holder: CategoryHolder<CardSimpleBinding>, position: Int) = with(holder.binding){
        holder.itemView.setOnClickListener { onItemClick(position) }
        barcode = getItem(position)
        executePendingBindings()
    }
}


abstract class  CategoryAdapter<T: Any,V: ViewDataBinding>(val layout: Int) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder<V>>(){
    private var items = listOf<T>()
    var onItemClick: (Int) -> Unit = {v -> Log.v("NOT BINDING","v = $v")}

    fun setItems(list: List<T>?){
        list?: return
        items = list
        notifyDataSetChanged()
        Log.w("ADAPTER","setItems list size = ${list.size}")
    }
    fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder<V> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<V>(inflater, layout, parent,false)
        return CategoryHolder<V>(binding)
    }

    override fun getItemCount()= items.size

    class CategoryHolder<V: ViewDataBinding>(val binding: V): RecyclerView.ViewHolder(binding.root)
}
