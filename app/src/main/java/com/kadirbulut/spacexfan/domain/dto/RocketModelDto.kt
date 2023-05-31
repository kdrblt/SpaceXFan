package com.kadirbulut.spacexfan.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// data transfer object for rocket model
@Parcelize
data class RocketModelDto(
    val id: String?,
    val name: String?,
    val type: String?,
    val country: String?,
    val company: String?,
    val wikipedia: String?,
    val first_flight: String?,
    val flickrImages: List<String?> = listOf(),
    val description: String?,
    val height: Height?,
    val diameter: Height?,
    val mass: Mass?
) : Parcelable {

    @Parcelize
    data class Height(
        val meters: Double?,
        val feet: Double?
    ) : Parcelable

    @Parcelize
    data class Mass(
        val kg: Double?,
        val lb: Double?
    ) : Parcelable
}
