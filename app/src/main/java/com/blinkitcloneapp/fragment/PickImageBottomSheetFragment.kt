package com.blinkitcloneapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blinkitcloneapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PickImageBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialog)
        return inflater.inflate(R.layout.fragment_pick_image_bottom_sheet, container, false)
    }

}