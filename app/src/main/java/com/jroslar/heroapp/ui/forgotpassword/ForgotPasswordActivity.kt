package com.jroslar.heroapp.ui.forgotpassword

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jroslar.heroapp.R
import com.jroslar.heroapp.core.dialog.ErrorDialog
import com.jroslar.heroapp.core.extensions.loseFocusAfterAction
import com.jroslar.heroapp.core.extensions.onTextChanged
import com.jroslar.heroapp.databinding.ActivityForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    private val forgotPasswordViewmodel: ForgotPasswordViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initUIState()
        initListener()
    }

    private fun initListener() {
        binding.tietForgotpasswordEmail.loseFocusAfterAction(EditorInfo.IME_ACTION_DONE)
        binding.tietForgotpasswordEmail.onTextChanged { onChangeField() }

        binding.btForgotpassword.setOnClickListener {
            if (!forgotPasswordViewmodel.state.value.isLoading) {
                forgotPasswordViewmodel.onClickForgotPassword(
                    binding.tietForgotpasswordEmail.text.toString()
                )
            }
        }
    }

    private fun onChangeField() {
        forgotPasswordViewmodel.onChangeText(
            binding.tietForgotpasswordEmail.text.toString()
        )
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                forgotPasswordViewmodel.state.collect {
                    updateUI(it)
                }
            }
        }
    }

    private fun updateUI(state: ForgotPasswordState) {
        with(binding) {
            pbLoading.isVisible = state.isLoading

            tilForgotpasswordEmail.error =
                if (state.isValidEmail) null else getString(R.string.ErrorEmail)
        }

        if (state.isError) showErrorDialog()
    }

    private fun showErrorDialog() {
        ErrorDialog.create(
            title = getString(R.string.forgotpasswordErrorDialogTitle),
            description = getString(R.string.forgotpasswordErrorDialogBody),
            positiveAction = ErrorDialog.Action(getString(R.string.forgotpasswordErrorDialogPositiveAction)) {
                it.dismiss()
            }
        ).show(supportFragmentManager, null)
    }
}