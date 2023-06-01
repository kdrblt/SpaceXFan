package com.kadirbulut.spacexfan.ui.rockets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.databinding.FragmentRocketsBinding
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import com.kadirbulut.spacexfan.ui.rockets.adapter.RocketsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketsFragment : BaseFragment<FragmentRocketsBinding>() {

    private val viewModel by viewModels<RocketsViewModel>()
    private val rocketsAdapter by lazy { RocketsAdapter() }
    override fun getLayoutRes(): Int = R.layout.fragment_rockets

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
            rockets.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is CallBack.OnError -> {
                            binding.progress.visibility = View.GONE
                        }
                        CallBack.OnLoading -> {
                            binding.progress.visibility = View.VISIBLE
                        }
                        is CallBack.OnSuccess -> {
                            setRocketsAdapter(it.data)
                            binding.progress.visibility = View.GONE
                        }
                    }
                }
            )
        }
    }

    private fun setRocketsAdapter(data: List<RocketModelDto>) {
        binding.rvRockets.adapter = rocketsAdapter
        binding.rvRockets.layoutManager = LinearLayoutManager(requireContext())
        rocketsAdapter.setList(
            data,
            viewModel.checkUserIsLogin()
        )
        rocketsAdapter.onRocketClicked = {
            navigateToDetail(it)
        }
        rocketsAdapter.addFavouriteClicked = {
            viewModel.addFavourites(it)
        }
        rocketsAdapter.removeFavouriteClicked = {
            viewModel.removeFavourites(it)
        }
    }
    private fun navigateToDetail(id: String) {
        findNavController().navigate(
            RocketsFragmentDirections.actionFromRocketsToRocketDetail(
                id
            )
        )
    }
}
