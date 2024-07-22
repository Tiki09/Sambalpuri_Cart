package com.blinkitcloneapp.auth

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.blinkitcloneapp.R
import com.blinkitcloneapp.Utils
import com.blinkitcloneapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater)
//        setStatusBarColor(
        getUserNumber()

        onContinueButtonClicked()

        return binding.root

    }

    private fun onContinueButtonClicked() {
        binding.btnContinue.setOnClickListener {
            val mob = binding.etPhone.text.toString()

            if (mob.isEmpty() && mob.length != 10){
                Utils.showToast(requireContext(), "Please enter valid phone number")
            }
            else{
                val bundle = Bundle()
                bundle.putString("mobile", mob)
                findNavController().navigate(R.id.action_signInFragment_to_OTPFragment, bundle)
            }
        }
    }

    private fun getUserNumber() {
        binding.etPhone.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(number: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val len = number?.length ?: 0

                    if (len == 10) {
                        binding.btnContinue.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green)
                    } else {
                        binding.btnContinue.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.grey)
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}

            }
        )
    }

    private fun setStatusBarColor() {
        val activity = activity
        if (activity != null) {
            val window = activity.window
            val context = context
            if (context != null) {
                val statusBarColors = ContextCompat.getColor(context, R.color.light_orange)
                window.statusBarColor = statusBarColors
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            }
        }
    }

}