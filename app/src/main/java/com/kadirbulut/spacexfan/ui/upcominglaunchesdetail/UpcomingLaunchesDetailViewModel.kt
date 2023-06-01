package com.kadirbulut.spacexfan.ui.upcominglaunchesdetail

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.di.StringResourcesProvider
import com.kadirbulut.spacexfan.domain.dto.LaunchModelDto
import com.kadirbulut.spacexfan.domain.usecase.remote.GetLaunchWithIdUseCase
import com.kadirbulut.spacexfan.ui.upcominglaunchesdetail.adapter.LaunchDetailItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UpcomingLaunchesDetailViewModel @Inject constructor(
    private val getLaunchWithIdUseCase: GetLaunchWithIdUseCase,
    private val stringResourcesProvider: StringResourcesProvider
) : ViewModel() {

    private val launchId = MutableLiveData<String>()

    private val _launchDetail = MutableLiveData<CallBack<LaunchModelDto>>(CallBack.OnLoading)
    val launchDetail: LiveData<CallBack<LaunchModelDto>> = _launchDetail

    private val _launchDetailItems = MutableLiveData<List<LaunchDetailItem>>()
    val launchDetailItems: LiveData<List<LaunchDetailItem>> = _launchDetailItems

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchExtras(arguments: Bundle) {
        with(UpcomingLaunchesDetailFragmentArgs.fromBundle(arguments)) {
            // get rocket incoming id from previous page as a bundle argument
            this@UpcomingLaunchesDetailViewModel.launchId.value = launchId
            getLaunchDetail()
        }
    }

    /*
     * Get detailed launch infos from spacex api
     */
    private fun getLaunchDetail() {
        viewModelScope.launch {
            _launchDetail.postValue(
                getLaunchWithIdUseCase.invoke(
                    GetLaunchWithIdUseCase.Params(
                        launchId = launchId.value.toString()
                    )
                )
            )
        }
    }

    /*
     * Create detailed infos list to show user
     */
    fun getDetailItems(data: LaunchModelDto) {
        _launchDetailItems.postValue(
            arrayListOf(
                LaunchDetailItem(
                    stringResourcesProvider.getString(R.string.launch_name_title),
                    data.name.toString()
                ),
                LaunchDetailItem(
                    stringResourcesProvider.getString(R.string.launch_date_title),
                    data.date_utc.toString()
                ),
                LaunchDetailItem(
                    stringResourcesProvider.getString(R.string.launch_flight_number_title),
                    data.flightNumber.toString()
                )
            )
        )
    }
}
