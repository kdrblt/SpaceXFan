package com.kadirbulut.spacexfan.ui.rocketdetail.adapter

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class RocketDetailItem(
    val tittle: String,
    var value: String
) : Parcelable
