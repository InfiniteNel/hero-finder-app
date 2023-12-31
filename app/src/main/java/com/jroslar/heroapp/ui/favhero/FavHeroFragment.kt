package com.jroslar.heroapp.ui.favhero

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jroslar.heroapp.R
import com.jroslar.heroapp.core.dialog.ErrorDialog
import com.jroslar.heroapp.databinding.FragmentFavHeroBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavHeroFragment : Fragment() {

    private var _binding:FragmentFavHeroBinding? = null
    private val binding get() = _binding!!

    private val favHeroViewModel:FavHeroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavHeroBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        initUIState()
        initListener()
    }

    private fun initListener() {
        binding.btSignOff.setOnClickListener { favHeroViewModel.signOff() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favHeroViewModel.state.collect {
                    when(it) {
                        FavHeroState.NoData -> noDataState()
                        is FavHeroState.Success -> successState(it)
                        FavHeroState.Error -> showErrorDialog(R.string.favHeroErrorDialogTitle, R.string.favHeroErrorDialogBody)
                        FavHeroState.SignOut -> signOutState()
                    }
                }
            }
        }
    }

    private fun signOutState() {
        activity?.finish()
    }

    private fun successState(favHeroState: FavHeroState.Success) {
        binding.tvUserEmail.text = favHeroState.email
        binding.tvUserName.text = favHeroState.userName
    }

    private fun noDataState() {
        binding.tvUserEmail.text = getString(R.string.tvFavHeroNoDataFound)
        binding.tvUserName.text = getString(R.string.tvFavHeroNoDataFound)
    }

    private fun showErrorDialog(title: Int, body: Int) {
        ErrorDialog.create(
            title = getString(title),
            description = getString(body),
            positiveAction = ErrorDialog.Action(getString(R.string.favHeroErrorDialogPositiveAction)) {
                favHeroViewModel.signOff()
                it.dismiss()
            },
            negativeAction = ErrorDialog.Action(getString(R.string.favHeroErrorDialogNegativeAction)) {
                it.dismiss()
            }
        ).show(parentFragmentManager, null)
    }
}