package com.kadirbulut.spacexfan.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.FragmentLoginBinding
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
    }
}
