package ru.yandex.dunaev.mick.barcodescanner


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import ru.yandex.dunaev.mick.barcodescanner.databinding.FragmentBarcodeBinding


class BarcodeFragment : Fragment() {
    private lateinit var binding: FragmentBarcodeBinding
    private lateinit var model: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_barcode,container,false)
        model = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        binding.viewModel = model

        return binding.root
    }


}
