package com.jroslar.heroapp.ui.login

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jroslar.heroapp.R
import com.jroslar.heroapp.core.extensions.loseFocusAfterAction
import com.jroslar.heroapp.core.extensions.onTextChanged
import com.jroslar.heroapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         binding = ActivityLoginBinding.inflate(layoutInflater)
         setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initListener()
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.state.collect {
                    updateUI(it)
                }
            }
        }
    }

    private fun updateUI(state: LoginState) {
        with(binding) {
            pbLoading.isVisible = state.isLoading
            tilLoginEmail.error =
                if (state.isValidEmail) null else getString(R.string.loginErrorEmail)
            tilLoginPassword.error =
                if (state.isValidPassword) null else getString(R.string.loginErrorPassword)
        }
    }

    private fun initListener() {
        binding.tietLoginEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.tietLoginEmail.onTextChanged { onChangeField() }

        binding.tietLoginPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.tietLoginPassword.onTextChanged { onChangeField() }

        binding.btLogin.setOnClickListener {
            if (!loginViewModel.state.value.isLoading) {
                loginViewModel.onLoginClick(
                    binding.tietLoginEmail.text.toString(),
                    binding.tietLoginPassword.text.toString()
                )
            }
        }

        binding.btToSignup.setOnClickListener {
            //
        }
    }

    private fun onChangeField() {
        loginViewModel.onChangeText(
            email = binding.tietLoginEmail.text.toString(),
            password = binding.tietLoginPassword.text.toString()
        )
    }
}