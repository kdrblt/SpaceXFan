package com.kadirbulut.spacexfan.ui.upcominglaunches

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.databinding.FragmentUpcomingLaunchesBinding
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import com.kadirbulut.spacexfan.ui.upcominglaunches.adapter.LaunchesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingLaunchesFragment : BaseFragment<FragmentUpcomingLaunchesBinding>() {
    private val viewModel by viewModels<UpcomingLaunchesViewModel>()
    private val launchesAdapter by lazy { LaunchesAdapter() }
    override fun getLayoutRes(): Int = R.layout.fragment_upcoming_launches

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    override fun initViews() {
    }

    private fun initObservers() {
        with(viewModel) {
            // top categories
            launches.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is CallBack.OnError -> {
                            isLoading.postValue(false)
                        }
                        CallBack.OnLoading -> {
                            isLoading.postValue(true)
                        }
                        is CallBack.OnSuccess -> {
                            binding.rvUpcomingLaunches.adapter = launchesAdapter
                            binding.rvUpcomingLaunches.layoutManager =
                                LinearLayoutManager(requireContext())
                            launchesAdapter.setList(it.data)
                            isLoading.postValue(false)
                            launchesAdapter.onLaunchClicked = {
                                navigateToDetail(it)
                            }
                        }
                    }
                }
            )
        }
    }

    private fun navigateToDetail(id: String) {
        findNavController().navigate(
            UpcomingLaunchesFragmentDirections.actionFromLaunchesToLaunchDetail(
                id
            )
        )
    }
}
