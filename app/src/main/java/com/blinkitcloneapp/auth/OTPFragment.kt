package com.blinkitcloneapp.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.blinkitcloneapp.R
import com.blinkitcloneapp.Utils
import com.blinkitcloneapp.databinding.FragmentOTPBinding
import com.blinkitcloneapp.viewmodels.AuthViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OTPFragment : Fragment() {

    private lateinit var binding: FragmentOTPBinding
    private var phnNumber: String? = null

    private val viewModel : AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOTPBinding.inflate(layoutInflater)

        getUserNumber()
        customizingEnteringOTP()
        onBackBtnClicked()
        sendOTP()
        onLoginBtnClicked()
        return binding.root
    }

    private fun onLoginBtnClicked() {
        binding.btnLogin.setOnClickListener {
            Utils.showDialog(requireContext(), "Signing you...")

            val editTxt = arrayOf(
                binding.etOtp1,
                binding.etOtp2,
                binding.etOtp3,
                binding.etOtp4,
                binding.etOtp5,
                binding.etOtp6
            )

            val otp = editTxt.joinToString(""){ it.text.toString() }
            if (otp.length < editTxt.size){
                Utils.showToast(requireContext(), "Please enter correct otp")
            }else{
                editTxt.forEach {
                    it.text?.clear()
                    it.clearFocus()
                }
                verifyOtp(otp)
            }
        }
    }

    private fun verifyOtp(otp: String) {
        viewModel.signInWithPhoneAuthCredential(otp, phnNumber?:"")

        lifecycleScope.launch {
            viewModel.isSignedInSuccess.collect{
                if (it){
                    Utils.hideDialog()
                    Utils.showToast(requireContext(),"Logged In...")
                }
            }
        }
    }


    private fun sendOTP() {
        Utils.showDialog(requireContext(), "Sending OTP...")
        viewModel.apply {
            phnNumber?.let { viewModel.sendOTP(phoneNumber = it, requireActivity()) }
            lifecycleScope.launch{
                otpSent.collect{
                    if(it == true){
                        Utils.hideDialog()
                        Utils.showToast(requireContext(), "OTP Sent...")
                    }
                }
            }
        }
    }

    private fun onBackBtnClicked() {
        binding.tbOtpFragment.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_OTPFragment_to_signInFragment)
        }
    }

    private fun customizingEnteringOTP() {
        val editTxt = arrayOf(
            binding.etOtp1,
            binding.etOtp2,
            binding.etOtp3,
            binding.etOtp4,
            binding.etOtp5,
            binding.etOtp6
        )
        for (i in editTxt.indices){
            editTxt[i].addTextChangedListener( object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(str: Editable?) {
                    if(str?.length == 1){
                        if (i < editTxt.size - 1){
                            editTxt[i+1].requestFocus()
                        }
                    }
                    else if(str?.length == 0){
                        if (i>0){
                            editTxt[i-1].requestFocus()
                        }
                    }
                }

            })
        }
    }

    private fun getUserNumber() {
        val bundle = arguments
        phnNumber = bundle?.getString("mobile")
        binding.tvMobNum.text = phnNumber
    }


}