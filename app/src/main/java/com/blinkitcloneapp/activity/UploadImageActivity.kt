package com.blinkitcloneapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blinkitcloneapp.databinding.ActivityUploadImageBinding
import com.blinkitcloneapp.fragment.PickImageBottomSheetFragment

class UploadImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadImageBinding
    lateinit var pickImgBottomSheetFragment: PickImageBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llAddImg.setOnClickListener {
            pickImgBottomSheetFragment = PickImageBottomSheetFragment()
            pickImgBottomSheetFragment.show(supportFragmentManager, "PickImageBottomSheet")
        }

    }
}