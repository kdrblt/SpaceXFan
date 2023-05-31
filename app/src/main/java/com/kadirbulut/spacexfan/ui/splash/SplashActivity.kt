package com.kadirbulut.spacexfan.ui.splash

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.kadirbulut.spacexfan.MainActivity
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.common.util.Constants
import com.kadirbulut.spacexfan.databinding.ActivitySplashBinding
import com.kadirbulut.spacexfan.domain.usecase.firebase.LogOutUseCase
import com.kadirbulut.spacexfan.ext.set
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    @Inject
    lateinit var logOutUseCase: LogOutUseCase
    private lateinit var binding: ActivitySplashBinding
    private lateinit var layout: View
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layout = binding.mainLayout
        sharedPreferences[Constants.SHARED_PREFS_USER_LOGIN_KEY] = false
        lifecycleScope.launch {
            logOutUseCase.invoke()
        }
        askInternetPermission(binding.tvSplashText)
    }

    private fun playAnimation() {
        binding.tvSplashText.visibility = View.VISIBLE
        val animation: Animation =
            AnimationUtils.loadAnimation(
                applicationContext,
                R.anim.anim_rotate
            )
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                startMainActivity()
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
        binding.tvSplashText.startAnimation(animation)
    }

    fun askInternetPermission(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) == PackageManager.PERMISSION_GRANTED -> {
                playAnimation()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.INTERNET
            ) -> {
                layout.showSnackbar(
                    view,
                    getString(R.string.application_permission_permission_needed),
                    Snackbar.LENGTH_INDEFINITE,
                    getString(R.string.application_permission_allow)
                ) {
                    requestPermissionLauncher.launch(
                        Manifest.permission.INTERNET
                    )
                }
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.INTERNET
                )
            }
        }
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java)).also { finish() }
    }
}

fun View.showSnackbar(
    view: View,
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(view, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }.show()
    } else {
        snackbar.show()
    }
}
