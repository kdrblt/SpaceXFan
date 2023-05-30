package com.kadirbulut.spacexfan.ui.rockets

import androidx.fragment.app.viewModels
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.FragmentRocketsBinding
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketsFragment : BaseFragment<FragmentRocketsBinding>() {

    private val viewModel by viewModels<RocketsViewModel>()
    override fun getLayoutRes(): Int = R.layout.fragment_rockets

    override fun initViews() {
        initAdapter()
        binding.textHome.text = "kadir bulut rockets"
    }

    private fun initAdapter() {
    }
}
