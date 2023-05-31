package com.kadirbulut.spacexfan.ui.rocketdetail

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.databinding.FragmentRocketDetailBinding
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import com.kadirbulut.spacexfan.ui.rocketdetail.adapter.RocketDetailInfoAdapter
import com.kadirbulut.spacexfan.ui.rocketdetail.adapter.RocketImagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketDetailFragment : BaseFragment<FragmentRocketDetailBinding>() {
    private val viewModel by viewModels<RocketDetailViewModel>()
    private val rocketImagesAdapter by lazy { RocketImagesAdapter() }
    private val rocketDetailsAdapter by lazy { RocketDetailInfoAdapter() }

    override fun getLayoutRes(): Int = R.layout.fragment_rocket_detail

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchExtras(requireArguments())
        initViews()
    }

    override fun initViews() {
        setObservers()
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setObservers() {
        with(viewModel) {
            rocketDetail.observe(
                viewLifecycleOwner,
                Observer {
                    when (it) {
                        is CallBack.OnError -> {
                        }

                        CallBack.OnLoading -> {
                        }

                        is CallBack.OnSuccess -> {
                            initSliderAdapter(it.data)
                            createDetailItems(it.data)
                        }
                    }
                }
            )

            rocketDetailItems.observe(
                viewLifecycleOwner,
                Observer {
                    binding.rvRocketDetailItems.adapter = rocketDetailsAdapter
                    binding.rvRocketDetailItems.layoutManager =
                        LinearLayoutManager(requireContext())
                    rocketDetailsAdapter.setList(it)
                }
            )
        }
    }

    private fun initSliderAdapter(data: RocketModelDto) {
        val imageList: ArrayList<String> = arrayListOf()
        data.flickrImages.forEach {
            imageList.add(it.toString())
        }
        binding.viewPagerRocketImages.adapter = rocketImagesAdapter
        rocketImagesAdapter.setList(imageList)
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPagerRocketImages
        ) { tab, position -> }.attach()
    }
}
