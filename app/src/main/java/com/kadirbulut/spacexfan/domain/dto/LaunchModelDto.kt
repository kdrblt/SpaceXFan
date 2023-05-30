package com.kadirbulut.spacexfan.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// data transfer object for launch model
@Parcelize
data class LaunchModelDto(
    val id: String?,
    val name: String?
) : Parcelable
