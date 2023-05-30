package com.kadirbulut.spacexfan.ui.upcominglaunches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.FragmentUpcomingLaunchesBinding
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingLaunchesFragment : BaseFragment<FragmentUpcomingLaunchesBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_upcoming_launches
    private val viewModel by viewModels<UpcomingLaunchesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun initViews() {
        binding.textNotifications.text = "upcoming launchess"
    }
}
