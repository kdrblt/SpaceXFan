package com.kadirbulut.spacexfan.ui.rocketdetail

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.common.util.Constants
import com.kadirbulut.spacexfan.di.StringResourcesProvider
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.domain.usecase.firebase.AddFavouriteUseCase
import com.kadirbulut.spacexfan.domain.usecase.firebase.RemoveFavouriteUseCase
import com.kadirbulut.spacexfan.domain.usecase.remote.GetRocketWithIdUseCase
import com.kadirbulut.spacexfan.ext.get
import com.kadirbulut.spacexfan.ui.rocketdetail.adapter.RocketDetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RocketDetailViewModel @Inject constructor(
    private val getRocketWithIdUseCase: GetRocketWithIdUseCase,
    private val stringResourcesProvider: StringResourcesProvider,
    private val sharedPreferences: SharedPreferences,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase
) : ViewModel() {

    private val rocketId = MutableLiveData<String>()
    val isFavouriteRocket = MutableLiveData(false)
    val isUserLogin = MutableLiveData(false)
    val isFromRocketsFragment = MutableLiveData(false)
    private val _rocketDetail = MutableLiveData<CallBack<RocketModelDto>>(CallBack.OnLoading)
    val rocketDetail: LiveData<CallBack<RocketModelDto>> = _rocketDetail
    private val _rocketDetailItems = MutableLiveData<List<RocketDetailItem>>()
    val rocketDetailItems: LiveData<List<RocketDetailItem>> = _rocketDetailItems

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchExtras(arguments: Bundle) {
        with(RocketDetailFragmentArgs.fromBundle(arguments)) {
            // get rocket incoming id from previous page as a bundle argument
            this@RocketDetailViewModel.rocketId.value = rocketId
            this@RocketDetailViewModel.isFavouriteRocket.value = isFavourite
            this@RocketDetailViewModel.isFromRocketsFragment.value = fromRocketsFragment
            getRocketDetail()
            isUserLogin.postValue(
                checkUserIsLogin()
            )
        }
    }

    /*
     * Get rocket details from spacexapi with unique rocket id
     */
    private fun getRocketDetail() {
        viewModelScope.launch {
            _rocketDetail.postValue(
                getRocketWithIdUseCase.invoke(
                    GetRocketWithIdUseCase.Params(
                        rocketId = this@RocketDetailViewModel.rocketId.value.toString()
                    )
                )
            )
        }
    }

    /*
     * Create an array to show detailed info for user
     */
    fun createDetailItems(data: RocketModelDto) {
        _rocketDetailItems.postValue(
            arrayListOf(
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_name_title),
                    data.name.toString()
                ),
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_type_title),
                    data.type.toString()
                ),
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_country_title),
                    data.country.toString()
                ),
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_company_title),
                    data.company.toString()
                ),
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_first_flight_title),
                    data.first_flight.toString()
                ),
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_height_title),
                    String.format(
                        stringResourcesProvider.getString(R.string.formatted_value_text),
                        data.height?.feet,
                        stringResourcesProvider.getString(R.string.feet)
                    )
                ),
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_mass_title),
                    String.format(
                        stringResourcesProvider.getString(R.string.formatted_value_text),
                        data.mass?.kg,
                        stringResourcesProvider.getString(R.string.kg)
                    )
                ),
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_wikipedia_title),
                    data.wikipedia.toString()
                ),
                RocketDetailItem(
                    stringResourcesProvider.getString(R.string.rocket_description_title),
                    data.description.toString()
                )
            )
        )
    }

    fun checkUserIsLogin(): Boolean =
        sharedPreferences.get(Constants.SHARED_PREFS_USER_LOGIN_KEY, false)

    /*
     * Add rocket into favourites
     */
    fun addFavourites(rocketId: String) {
        viewModelScope.launch {
            addFavouriteUseCase.invoke(
                AddFavouriteUseCase.Params(
                    getUserEmail(),
                    rocketId
                )
            )
        }
    }

    /*
     * Add rocket from favourites
     */
    fun removeFavourites(rocketId: String) {
        viewModelScope.launch {
            removeFavouriteUseCase.invoke(
                RemoveFavouriteUseCase.Params(
                    getUserEmail(),
                    rocketId
                )
            )
        }
    }

    private fun getUserEmail(): String = sharedPreferences.get(
        Constants.SHARED_PREFS_USER_USER_EMAIL_KEY, ""
    )

    /*
     * Listen favority changing from favourite toggle button
     */
    fun changeFavority(checked: Boolean) {
        if (checked) {
            addFavourites(rocketId.value.toString())
        } else {
            removeFavourites(rocketId.value.toString())
        }
    }
}
