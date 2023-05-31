package com.kadirbulut.spacexfan.ui.upcominglaunchesdetail

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.databinding.FragmentUpcomingLaunchesDetailBinding
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import com.kadirbulut.spacexfan.ui.upcominglaunchesdetail.adapter.UpcomingLaunchDetailInfoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingLaunchesDetailFragment : BaseFragment<FragmentUpcomingLaunchesDetailBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_upcoming_launches_detail
    private val viewModel by viewModels<UpcomingLaunchesDetailViewModel>()
    private val launchDetailsAdapter by lazy { UpcomingLaunchDetailInfoAdapter() }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchExtras(requireArguments())
        initViews()
    }

    override fun initViews() {
        setClickListeners()
        setObservers()
    }

    private fun setClickListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setObservers() {
        with(viewModel) {
            launchDetail.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is CallBack.OnError -> {
                        }
                        CallBack.OnLoading -> {
                        }
                        is CallBack.OnSuccess -> {
                            viewModel.getDetailItems(it.data)
                        }
                    }
                }
            )

            launchDetailItems.observe(
                viewLifecycleOwner,
                Observer {
                    binding.rvLaunchDetails.adapter = launchDetailsAdapter
                    binding.rvLaunchDetails.layoutManager =
                        LinearLayoutManager(requireContext())
                    launchDetailsAdapter.setList(it)
                }
            )
        }
    }
}
