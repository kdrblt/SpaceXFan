package com.kadirbulut.spacexfan.ui.upcominglaunchesdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.FragmentUpcomingLaunchesDetailBinding
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingLaunchesDetailFragment : BaseFragment<FragmentUpcomingLaunchesDetailBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_upcoming_launches_detail
    private val viewModel by viewModels<UpcomingLaunchesDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun initViews() {
        TODO("Not yet implemented")
    }
}
