package ru.yandex.dunaev.mick.barcodescanner


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraUtils
import com.otaliastudios.cameraview.Gesture
import com.otaliastudios.cameraview.GestureAction
import ru.yandex.dunaev.mick.barcodescanner.databinding.FragmentCameraBinding


class CameraFragment : Fragment() {

    var onCaptureComplete: () -> Unit = {}

    private lateinit var binding: FragmentCameraBinding
    private lateinit var model: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_camera,container,false)
        model = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        binding.viewModel = model
        binding.fragment = this
        binding.camera.setLifecycleOwner(this)
        binding.camera.apply {
            mapGesture(Gesture.PINCH, GestureAction.ZOOM)
            mapGesture(Gesture.SCROLL_VERTICAL, GestureAction.EXPOSURE_CORRECTION)
            mapGesture(Gesture.TAP, GestureAction.FOCUS_WITH_MARKER)

            addCameraListener(object: CameraListener() {
                override fun onPictureTaken(jpeg: ByteArray?) {
                    super.onPictureTaken(jpeg)
                    CameraUtils.decodeBitmap(jpeg,4096, 4096) {
                        model.setImage(it)
                        onCaptureComplete()
                    }
                }
            })
        }

        return binding.root
    }

    fun onCaptureImage(){
        Log.v("PhotoCapture","Capture")
        binding.camera.capturePicture()
    }

}
