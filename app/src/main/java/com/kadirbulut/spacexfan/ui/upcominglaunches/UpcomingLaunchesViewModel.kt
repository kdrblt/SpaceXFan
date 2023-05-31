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

    val isLoading = MutableLiveData(true)
    private val _launches = MutableLiveData<CallBack<List<LaunchModelDto>>>(CallBack.OnLoading)
    val launches: LiveData<CallBack<List<LaunchModelDto>>> = _launches

    init {
        isLoading.postValue(true) // start with loading until request is finished
        getLaunches() // send request
    }

    private fun getLaunches() {
        viewModelScope.launch {
            _launches.value = getLaunchesUseCase.invoke()
        }
    }
}
