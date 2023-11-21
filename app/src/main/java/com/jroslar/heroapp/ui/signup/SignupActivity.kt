package com.jroslar.heroapp.ui.signup

import android.content.Intent
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
import com.jroslar.heroapp.databinding.ActivitySignupBinding
import com.jroslar.heroapp.ui.MainActivity
import com.jroslar.heroapp.ui.signup.model.UserSignupData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val signupViewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
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
                signupViewModel.state.collect {
                    updateUI(it)
                }
            }
        }
    }

    private fun updateUI(state: SignupState) {
        with(binding) {
            pbLoading.isVisible = state.isLoading

            tilSignupEmail.error = if (state.isValidEmail) null else getString(R.string.ErrorEmail)
            tilSignupUsername.error = if (state.isValidUsername) null else getString(R.string.ErrorUsername)
            tilSignupPassword.error = if (state.isValidPassword) null else getString(R.string.ErrorPassword)
            tilSignupRepeatPassword.error = if (state.isValidRepeatPassword) null else getString(R.string.ErrorRepeatPassword)
        }

        if (state.isSuccess) navigateToMain()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun initListener() {
        binding.tietSignupEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.tietSignupEmail.onTextChanged { onChangeField() }

        binding.tietSignupUsername.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.tietSignupUsername.onTextChanged { onChangeField() }

        binding.tietSignupPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_NEXT)
        binding.tietSignupPassword.onTextChanged { onChangeField() }

        binding.tietSignupRepeatPassword.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.tietSignupRepeatPassword.onTextChanged { onChangeField() }

        binding.tvToLogin.setOnClickListener { if (!signupViewModel.state.value.isLoading) navigateToLogin() }

        binding.btSignup.setOnClickListener { if (!signupViewModel.state.value.isLoading) signupViewModel.onClickSignup(getData()) }
    }

    private fun navigateToLogin() {
        finish()
    }

    private fun onChangeField() {
        signupViewModel.onChangeText(getData())
    }

    private fun getData(): UserSignupData {
        return UserSignupData(
            binding.tietSignupEmail.text.toString(),
            binding.tietSignupUsername.text.toString(),
            binding.tietSignupPassword.text.toString(),
            binding.tietSignupRepeatPassword.text.toString()
        )
    }
}