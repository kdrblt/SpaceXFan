package com.kadirbulut.spacexfan.ui.upcominglaunchesdetail.adapter

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class LaunchDetailItem(
    val title: String,
    var value: String
) : Parcelable
