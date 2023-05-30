package com.kadirbulut.spacexfan.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.FragmentFavouritesBinding
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_favourites
    private val viewModel by viewModels<FavouritesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    override fun initViews() {
        binding.textDashboard.text = "favouritessss"
    }
}
