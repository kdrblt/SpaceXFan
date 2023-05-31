package com.kadirbulut.spacexfan.domain.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// data transfer object for launch model
@Parcelize
data class LaunchModelDto(
    val id: String?,
    val name: String?,
    val date_utc: String?,
    var flightNumber: Int?,
    val links: Links?
) : Parcelable {

    @Parcelize
    data class Links(
        var patch: Patch?,
        var reddit: Reddit?,
        var flickr: Flickr?,
        var presskit: String?,
        var webcast: String?,
        var youtubeId: String?,
        var article: String?,
        var wikipedia: String?
    ) : Parcelable

    @Parcelize
    data class Patch(
        var small: String?,
        var large: String?
    ) : Parcelable

    @Parcelize
    data class Reddit(
        var campaign: String?,
        var launch: String?,
        var media: String?,
        var recovery: String?
    ) : Parcelable

    @Parcelize
    data class Flickr(
        var small: List<String>,
        var original: List<String>
    ) : Parcelable
}
