package com.john.gotouchgrass.ui.order

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.john.gotouchgrass.R
import com.john.gotouchgrass.databinding.FragmentHomeScreenBinding
import com.john.gotouchgrass.viewmodel.GrassViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log

class HomeScreen : Fragment() {
    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var photoFile: File
    lateinit var currentPhotoPath: String


    //    private val viewModel: GrassViewModel by viewModels {
//        GrassViewModelFactory(requireActivity().application)
//    }
    private val viewModel: GrassViewModel by activityViewModels()
    val REQUEST_CODE = 200
    lateinit var logo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.houseButton?.setOnClickListener {
            viewModel.stopTime()
            findNavController().navigate(R.id.action_homeScreen_to_frag_rating)
        }


//        var city: String? = viewModel.getCity()
//        if (city != null) {
//            weatherViewModel.getTemp(city, viewModel)
//        }


//        if (viewModel.getTemp() != null) {
//            binding.tempTxt?.text = viewModel.getTemp()
//        }

        super.onCreate(savedInstanceState)
        root.setOnTouchListener(object : OnSwipeTouchListener(activity) {
            override fun onSwipeLeft() {
                super.onSwipeLeft()
                Log.d( "Left", "WENT left")
                Toast.makeText(activity, "Swipe Left gesture detected",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onSwipeRight() {
                super.onSwipeRight()
                Log.d( "Right", "WENT right")
                Toast.makeText(
                    activity,
                    "Swipe Right gesture detected",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onSwipeUp() {
                super.onSwipeUp()
                Toast.makeText(activity, "Swipe up gesture detected", Toast.LENGTH_SHORT)
                    .show()
            }
            override fun onSwipeDown() {
                super.onSwipeDown()
                Toast.makeText(activity, "Swipe down gesture detected", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        binding.cameraButton.setOnClickListener {
            if (activity?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        Manifest.permission.CAMERA
                    )
                } !==
                PackageManager.PERMISSION_GRANTED) {
                Log.d("hello", "not granted")
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
                } else {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), 1)
                }
            } else {
                Log.d("hello", "already granted")
                takePicture()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((activity?.let {
                            ActivityCompat.checkSelfPermission(
                                it,
                                Manifest.permission.CAMERA)
                        } ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
                        takePicture()
                    }
                } else {
                    Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    fun capturePhoto() {
        Log.d("hello", "taking photo")
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    private fun takePicture(){
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = createImageFile()
        val uri= activity?.let { FileProvider.getUriForFile(it,"com.john.gotouchgrass.fileprovider", photoFile) }
        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(pictureIntent, REQUEST_CODE)
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            logo = binding.iv
            val uri = activity?.let { FileProvider.getUriForFile(it, "com.john.gotouchgrass.fileprovider", photoFile) }
            if (uri != null) {
                viewModel.storeImage(uri)
            }
            Log.d("hello", uri.toString())
            logo.setImageURI(uri)
        } else {
            Log.d("hello", "no uri")
            Log.d("hello", photoFile.absolutePath)
            Log.d("hello", currentPhotoPath)
            Log.d("hello", (resultCode == Activity.RESULT_OK).toString())
            Log.d("hello", (requestCode == REQUEST_CODE).toString())
            Log.d("hello", (data != null).toString())
        }
    }
}

