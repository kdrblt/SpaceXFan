package com.kadirbulut.spacexfan.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// data transfer object for rocket model
@Parcelize
data class RocketModelDto(
    val id: String?,
    val name: String?,
    val flickrImages: List<String?> = listOf(),
    val description: String?
) : Parcelable
