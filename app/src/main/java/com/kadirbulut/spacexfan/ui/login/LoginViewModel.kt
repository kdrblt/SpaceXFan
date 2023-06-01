package com.kadirbulut.spacexfan.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.common.util.Constants
import com.kadirbulut.spacexfan.domain.usecase.firebase.LoginUseCase
import com.kadirbulut.spacexfan.ext.set
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _user = MutableLiveData<CallBack<FirebaseUser>>()
    val user: LiveData<CallBack<FirebaseUser>> = _user

    /*
     * If login is clicked, make firebase service call for authentication
     */
    fun loginClicked(email: String, password: String) {
        viewModelScope.launch {
            _user.postValue(CallBack.OnLoading)
            _user.postValue(
                loginUseCase.invoke(
                    LoginUseCase.Params(
                        email, password
                    )
                )
            )
        }
    }

    /*
     * If login is success, save login status and user email
     * User email is used for firebase store operations
     */
    fun saveUserLoginSuccess(userEmail: String) {
        sharedPreferences.set(Constants.SHARED_PREFS_USER_LOGIN_KEY, true)
        sharedPreferences.set(Constants.SHARED_PREFS_USER_USER_EMAIL_KEY, userEmail)
    }
}
