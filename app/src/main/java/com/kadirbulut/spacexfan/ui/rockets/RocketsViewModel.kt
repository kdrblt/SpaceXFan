package com.kadirbulut.spacexfan.ui.rockets

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.common.util.Constants
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.domain.usecase.firebase.AddFavouriteUseCase
import com.kadirbulut.spacexfan.domain.usecase.firebase.GetFavouriteRocketsUseCase
import com.kadirbulut.spacexfan.domain.usecase.firebase.RemoveFavouriteUseCase
import com.kadirbulut.spacexfan.domain.usecase.remote.GetRocketsUseCase
import com.kadirbulut.spacexfan.ext.get
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RocketsViewModel @Inject constructor(
    private val getRocketsUseCase: GetRocketsUseCase,
    private val getFavouriteRocketsUseCase: GetFavouriteRocketsUseCase,
    private val sharedPreferences: SharedPreferences,
    private val addFavouriteUseCase: AddFavouriteUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase
) : ViewModel() {

    val isUserLogin = MutableLiveData<Boolean>(false)

    private val _rockets = MutableLiveData<CallBack<List<RocketModelDto>>>(CallBack.OnLoading)
    val rockets: LiveData<CallBack<List<RocketModelDto>>> = _rockets

    private val userFavouriteList = MutableLiveData<List<String>>()

    init {
        isUserLogin.postValue(checkUserIsLogin())
        getRockets() // send request
        checkUserIsLogin()
    }

    private fun getRockets() {
        viewModelScope.launch {
            _rockets.value = CallBack.OnLoading
            val rocketListCallResult = getRocketsUseCase.invoke()
            if (!checkUserIsLogin())
                _rockets.value = rocketListCallResult
            else
                getFavouriteList(rocketListCallResult)
        }
    }

    fun checkUserIsLogin() = sharedPreferences.get(
        Constants.SHARED_PREFS_USER_LOGIN_KEY,
        false
    )

    private fun getFavouriteList(rocketListCallResult: CallBack<List<RocketModelDto>>) {
        val email = sharedPreferences.get(
            Constants.SHARED_PREFS_USER_USER_EMAIL_KEY,
            ""
        )
        if (email.isNotEmpty()) {
            viewModelScope.launch {
                val task = getFavouriteRocketsUseCase.invoke(
                    GetFavouriteRocketsUseCase.Params(
                        email
                    )
                )
                when (task) {
                    is CallBack.OnError -> {
                    }

                    CallBack.OnLoading -> TODO()
                    is CallBack.OnSuccess -> {
                        task.data.addOnSuccessListener {
                            userFavouriteList.value = getFavouriteListFromTaskResult(it.data)
                            setRocketsWithFavouriteAttribute(rocketListCallResult)
                        }
                        task.data.addOnFailureListener {
                            _rockets.postValue(rocketListCallResult)
                        }
                    }
                }
            }
        }
    }

    private fun setRocketsWithFavouriteAttribute(
        rocketListCallResult: CallBack<List<RocketModelDto>>
    ) {
        when (rocketListCallResult) {
            is CallBack.OnError -> {}
            CallBack.OnLoading -> {}
            is CallBack.OnSuccess -> {
                val rocketList = rocketListCallResult.data
                rocketList.forEach {
                    if (userFavouriteList.value != null) {
                        if (userFavouriteList.value!!.contains(it.id.toString())) {
                            it.isFavourite = true
                        } else {
                        }
                    }
                }
                _rockets.value = CallBack.OnSuccess(rocketList)
            }
        }
    }

    private fun getFavouriteListFromTaskResult(data: MutableMap<String, Any>?) =
        data?.values?.toString()
            ?.trim()
            ?.replace("[", "")
            ?.replace("]", "")
            ?.replace(" ", "")
            ?.split(",")

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
}
