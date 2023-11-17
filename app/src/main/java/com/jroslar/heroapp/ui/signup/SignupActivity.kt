package com.jroslar.heroapp.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jroslar.heroapp.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {

    }
}