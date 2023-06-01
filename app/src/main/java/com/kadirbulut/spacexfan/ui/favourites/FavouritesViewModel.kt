package com.kadirbulut.spacexfan.ui.favourites

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.common.util.Constants
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.domain.usecase.firebase.GetFavouriteRocketsUseCase
import com.kadirbulut.spacexfan.domain.usecase.firebase.RemoveFavouriteUseCase
import com.kadirbulut.spacexfan.domain.usecase.remote.GetRocketWithIdUseCase
import com.kadirbulut.spacexfan.ext.get
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val getFavouriteRocketsUseCase: GetFavouriteRocketsUseCase,
    private val getRocketWithIdUseCase: GetRocketWithIdUseCase,
    private val removeFavouriteUseCase: RemoveFavouriteUseCase
) : ViewModel() {

    private val _rockets = MutableLiveData<CallBack<List<RocketModelDto>>>(CallBack.OnLoading)
    val rockets: LiveData<CallBack<List<RocketModelDto>>> = _rockets
    private val userFavouriteList = arrayListOf<String>()

    init {
        /*
         * If user is login get favourite rocket list
         */
        if (checkUserIsLogin())
            getFavouriteList()
    }

    /*
     * Get favourite rockets from spacex api with saved favourite rocked ids in firebase
     */
    private fun getFavouriteRockets() {
        if (!userFavouriteList.isEmpty()) {
            viewModelScope.launch {
                _rockets.value = CallBack.OnLoading
                val rocketResults = arrayListOf<RocketModelDto>()
                userFavouriteList.forEach {
                    val tempResult = getRocketWithIdUseCase.invoke(
                        GetRocketWithIdUseCase.Params(
                            it
                        )
                    )
                    if (tempResult is CallBack.OnSuccess) {
                        rocketResults.add(
                            tempResult.data
                        )
                        _rockets.value = CallBack.OnSuccess(rocketResults)
                    }
                }
            }
        } else {
            _rockets.value = CallBack.OnSuccess(listOf())
        }
    }

    fun checkUserIsLogin(): Boolean =
        sharedPreferences.get(Constants.SHARED_PREFS_USER_LOGIN_KEY, false)

    /*
     * Get favourite rocket ids from firebase store
     */
    private fun getFavouriteList() {
        val email = sharedPreferences.get(
            Constants.SHARED_PREFS_USER_USER_EMAIL_KEY,
            ""
        )
        if (email.isNotEmpty()) {
            viewModelScope.launch {
                _rockets.value = CallBack.OnLoading
                val task = getFavouriteRocketsUseCase.invoke(
                    GetFavouriteRocketsUseCase.Params(
                        email
                    )
                )
                when (task) {
                    is CallBack.OnError -> {
                    }

                    CallBack.OnLoading -> {
                    }

                    is CallBack.OnSuccess -> {
                        task.data.addOnSuccessListener {
                            val res = getFavouriteListFromTaskResult(it.data)
                            if (res != null) {
                                if (res.isNotEmpty()) {
                                    if (res.get(0).length > 0) {
                                        userFavouriteList.addAll(res)
                                        getFavouriteRockets()
                                    }
                                } else {
                                    userFavouriteList.clear()
                                    _rockets.value = CallBack.OnSuccess(listOf())
                                }
                            }
                        }
                        task.data.addOnFailureListener {
                            _rockets.value = CallBack.OnError(it)
                        }
                    }
                }
            }
        }
    }

    private fun getFavouriteListFromTaskResult(data: MutableMap<String, Any>?): List<String>? {
        val res = data?.get(Constants.DB_ROCKETS_FIELD_NAME).toString()
        val str = res
            .trim()
            .replace("[", "")
            .replace("]", "")
            .replace(" ", "")
        return if (str.isEmpty()) listOf() else str.split(",")
    }

    /*
     * Removes rocket from favourites
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
}
