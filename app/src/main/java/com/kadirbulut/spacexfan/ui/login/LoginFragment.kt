package com.kadirbulut.spacexfan.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.databinding.FragmentLoginBinding
import com.kadirbulut.spacexfan.ext.toast
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel by viewModels<LoginViewModel>()
    override fun getLayoutRes(): Int = R.layout.fragment_login

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    override fun initViews() {
        setClickListeners()
        setObservers()
    }

    private fun setClickListeners() {
        binding.btnLogin.setOnClickListener {
            viewModel.loginClicked(
                binding.etLoginEmail.text.toString(),
                binding.etLoginPassword.text.toString()
            )
        }
    }

    /*
     * This func. observes login operation
     * Makes some saving status operations
     * Goes related page, favourites fragment
     */
    private fun setObservers() {
        with(viewModel) {
            user.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is CallBack.OnError -> {
                            binding.progress.visibility = View.GONE
                            this@LoginFragment.context?.toast(it.error.message.toString())
                        }
                        CallBack.OnLoading -> {
                            binding.progress.visibility = View.VISIBLE
                        }
                        is CallBack.OnSuccess -> {
                            binding.progress.visibility = View.GONE
                            viewModel.saveUserLoginSuccess(binding.etLoginEmail.text.toString())
                            navigateToFavourites()
                        }
                    }
                }
            )
        }
    }

    private fun navigateToFavourites() {
        findNavController().navigate(
            R.id.navigation_favourites
        )
    }
}
