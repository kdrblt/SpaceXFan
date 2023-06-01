package com.kadirbulut.spacexfan.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.databinding.FragmentFavouritesBinding
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.ui.base.BaseFragment
import com.kadirbulut.spacexfan.ui.favourites.adapter.FavouritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_favourites
    private val viewModel by viewModels<FavouritesViewModel>()
    private val favouritesAdapter by lazy { FavouritesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isUserLogin = viewModel.checkUserIsLogin()
        if (isUserLogin) {
            initViews()
        } else {
            navigateToLogin()
        }
    }

    override fun initViews() {
        viewModel.checkUserIsLogin()
        setObservers()
    }

    private fun setObservers() {
        /*
         * Observe favourites to show them
         */
        with(viewModel) {
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
                            if (it.data.isNotEmpty())
                                setFavouritesAdapter(it.data)
                            else {
                                binding.nestedScroolView.visibility = View.GONE
                                binding.clEmptyCase.visibility = View.VISIBLE
                            }
                            binding.progress.visibility = View.GONE
                        }
                    }
                }
            )
        }
    }

    /*
     * This func. sets adapter for favourites
     * With listeners, it removes items from favourites, goes to rocket detail page
     * At the same time, it shows empty screen design if all favourites are removed
     */
    private fun setFavouritesAdapter(data: List<RocketModelDto>) {
        binding.rvFavourites.adapter = favouritesAdapter
        binding.rvFavourites.layoutManager = LinearLayoutManager(requireContext())
        favouritesAdapter.setList(
            data
        )
        favouritesAdapter.onRocketClicked = { rocketId: String, isFavourite: Boolean ->
            navigateToRocketDetail(rocketId, isFavourite)
        }
        favouritesAdapter.removeFavouriteClicked = {
            viewModel.removeFavourites(it)
        }
        favouritesAdapter.allItemsRemoved = {
            binding.clEmptyCase.visibility = View.VISIBLE
            binding.nestedScroolView.visibility = View.GONE
        }
    }

    /*
     * Navigates to rocket detail page with it id and state
     */
    private fun navigateToRocketDetail(rocketId: String, isFavourite: Boolean) {
        findNavController().navigate(
            FavouritesFragmentDirections.actionFavouritesToRocketDetail(
                rocketId,
                isFavourite
            )
        )
    }

    /*
     * if user is not login, navigates to login fragment
     */
    private fun navigateToLogin() {
        findNavController().navigate(
            FavouritesFragmentDirections.actionFavouritesToLogin()
        )
    }
}
