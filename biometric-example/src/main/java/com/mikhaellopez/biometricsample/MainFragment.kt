package com.mikhaellopez.biometricsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mikhaellopez.biometric.BiometricHelper
import com.mikhaellopez.biometric.BiometricPromptInfo
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }

    private val biometricHelper by lazy { BiometricHelper(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textBiometricType.text = getString(R.string.biometric_type, biometricHelper.getBiometricType())
        btnPrompt.visibility = if (biometricHelper.biometricEnable()) View.VISIBLE else View.GONE

        btnPrompt.setOnClickListener {
            biometricHelper.showBiometricPrompt(
                BiometricPromptInfo(
                    title = "Custom title (editable & mandatory)",
                    negativeButtonText = "Cancel (editable & mandatory)"
                )
            ) {
                // Do something when success

            }
        }
    }

}