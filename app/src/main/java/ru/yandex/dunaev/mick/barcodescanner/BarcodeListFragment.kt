package ru.yandex.dunaev.mick.barcodescanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yandex.dunaev.mick.barcodescanner.databinding.FragmentBarcodeListBinding

class BarcodeListFragment : Fragment() {
    private lateinit var binding: FragmentBarcodeListBinding
    private lateinit var model: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_barcode_list,container,false)
        model = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        binding.viewModel = model
        binding.layout = LinearLayoutManager(activity)

        return binding.root
    }
}
