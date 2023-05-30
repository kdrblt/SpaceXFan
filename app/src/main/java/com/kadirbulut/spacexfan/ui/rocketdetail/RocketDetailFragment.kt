package com.kadirbulut.spacexfan.ui.rocketdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.FragmentRocketDetailBinding
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketDetailFragment : BaseFragment<FragmentRocketDetailBinding>() {
    private val viewModel by viewModels<RocketDetailViewModel>()
    override fun getLayoutRes(): Int = R.layout.fragment_rocket_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    override fun initViews() {
    }
}
