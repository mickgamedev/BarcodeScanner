package ru.yandex.dunaev.mick.barcodescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.florent37.runtimepermission.kotlin.askPermission
import ru.yandex.dunaev.mick.barcodescanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        askPermission{}.onDeclined { finish() }
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        model = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = model
        binding.menuBottom.apply{
            setOnNavigationItemSelectedListener { menuItemSelected(it) }
            selectedItemId = R.id.camera_id
        }

    }

    private fun menuItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.camera_id -> setFragment(CameraFragment().apply {
                onCaptureComplete = this@MainActivity::onCaptureComplete
            })
            R.id.barcode_id -> setFragment(BarcodeFragment())
        }
        return true
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun onCaptureComplete(){
        binding.menuBottom.selectedItemId = R.id.barcode_id
    }
}
