package com.kadirbulut.spacexfan.ui.rockets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.domain.usecase.remote.GetRocketsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val getRocketsUseCase: GetRocketsUseCase
) : ViewModel() {
    val isLoading = MutableLiveData(true)
    private val _rockets = MutableLiveData<CallBack<List<RocketModelDto>>>(CallBack.OnLoading)
    val rockets: LiveData<CallBack<List<RocketModelDto>>> = _rockets

    init {
        isLoading.postValue(true) // start with loading until request is finished
        getRockets() // send request
    }

    private fun getRockets() {
        viewModelScope.launch {
            _rockets.value = getRocketsUseCase.invoke()
        }
    }
}
