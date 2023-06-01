package com.kadirbulut.spacexfan.ui.upcominglaunches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.domain.dto.LaunchModelDto
import com.kadirbulut.spacexfan.domain.usecase.remote.GetLaunchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UpcomingLaunchesViewModel @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase
) : ViewModel() {

    private val _launches = MutableLiveData<CallBack<List<LaunchModelDto>>>(CallBack.OnLoading)
    val launches: LiveData<CallBack<List<LaunchModelDto>>> = _launches

    init {
        getLaunches() // send request
    }

    /*
     * Get upcoming launches from spacex api
     */
    private fun getLaunches() {
        viewModelScope.launch {
            _launches.value = CallBack.OnLoading
            _launches.value = getLaunchesUseCase.invoke()
        }
    }
}
