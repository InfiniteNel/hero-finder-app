package com.jroslar.heroapp.ui.login

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.jroslar.heroapp.core.extensions.loseFocusAfterAction
import com.jroslar.heroapp.core.extensions.onTextChanged
import com.jroslar.heroapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityLoginBinding.inflate(layoutInflater)
         setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initListener()
    }

    private fun initListener() {
        binding.tietLoginEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.tietLoginEmail.onTextChanged {  }

        binding.tietLoginPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.tietLoginPassword.onTextChanged {  }
    }
}