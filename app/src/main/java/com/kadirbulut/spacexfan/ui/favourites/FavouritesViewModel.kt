package com.kadirbulut.spacexfan.ui.favourites

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.kadirbulut.spacexfan.common.util.Constants
import com.kadirbulut.spacexfan.ext.get
import com.kadirbulut.spacexfan.ext.set
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun checkUserIsLogin(): Boolean =
        sharedPreferences.get(Constants.SHARED_PREFS_USER_LOGIN_KEY, false)
}
