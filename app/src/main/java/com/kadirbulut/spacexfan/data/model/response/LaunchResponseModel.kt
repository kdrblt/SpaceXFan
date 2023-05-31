package com.kadirbulut.spacexfan.data.model.response

import com.google.gson.annotations.SerializedName

// launch response model
data class LaunchResponseModel(

    @SerializedName("fairings") var fairings: Fairings?,
    @SerializedName("links") var links: Links?,
    @SerializedName("static_fire_date_utc") var staticFireDateUtc: String?,
    @SerializedName("static_fire_date_unix") var staticFireDateUnix: Int?,
    @SerializedName("tdb") var tdb: Boolean?,
    @SerializedName("net") var net: Boolean?,
    @SerializedName("window") var window: Int?,
    @SerializedName("rocket") var rocket: String?,
    @SerializedName("success") var success: Boolean?,
    @SerializedName("failures") var failures: List<Failures?>?,
    @SerializedName("details") var details: String?,
    @SerializedName("crew") var crew: List<Crew?>?,
    @SerializedName("ships") var ships: List<String?>?,
    @SerializedName("capsules") var capsules: List<String?>?,
    @SerializedName("payloads") var payloads: List<String?>?,
    @SerializedName("launchpad") var launchpad: String?,
    @SerializedName("auto_update") var autoUpdate: Boolean?,
    @SerializedName("flight_number") var flightNumber: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("date_utc") var dateUtc: String?,
    @SerializedName("date_unix") var dateUnix: Int?,
    @SerializedName("date_local") var dateLocal: String?,
    @SerializedName("date_precision") var datePrecision: String?,
    @SerializedName("upcoming") var upcoming: Boolean?,
    @SerializedName("cores") var cores: List<Cores>,
    @SerializedName("id") var id: String?
) {

    data class Fairings(

        @SerializedName("reused") var reused: Boolean?,
        @SerializedName("recovery_attempt") var recoveryAttempt: Boolean?,
        @SerializedName("recovered") var recovered: Boolean?,
        @SerializedName("ships") var ships: List<String?>?

    )

    data class Crew(
        @SerializedName("crew") var crew: String?,
        @SerializedName("role") var role: String?
    )

    data class Patch(
        @SerializedName("small") var small: String?,
        @SerializedName("large") var large: String?
    )

    data class Reddit(
        @SerializedName("campaign") var campaign: String?,
        @SerializedName("launch") var launch: String?,
        @SerializedName("media") var media: String?,
        @SerializedName("recovery") var recovery: String?
    )

    data class Flickr(
        @SerializedName("small") var small: List<String>,
        @SerializedName("original") var original: List<String>
    )

    data class Links(
        @SerializedName("patch") var patch: Patch?,
        @SerializedName("reddit") var reddit: Reddit?,
        @SerializedName("flickr") var flickr: Flickr?,
        @SerializedName("presskit") var presskit: String?,
        @SerializedName("webcast") var webcast: String?,
        @SerializedName("youtube_id") var youtubeId: String?,
        @SerializedName("article") var article: String?,
        @SerializedName("wikipedia") var wikipedia: String?
    )

    data class Cores(
        @SerializedName("core") var core: String? = null,
        @SerializedName("flight") var flight: Int? = null,
        @SerializedName("gridfins") var gridfins: Boolean? = null,
        @SerializedName("legs") var legs: Boolean? = null,
        @SerializedName("reused") var reused: Boolean? = null,
        @SerializedName("landing_attempt") var landingAttempt: Boolean? = null,
        @SerializedName("landing_success") var landingSuccess: Boolean? = null,
        @SerializedName("landing_type") var landingType: String? = null,
        @SerializedName("landpad") var landpad: String? = null
    )

    data class Failures(

        @SerializedName("time") var time: Int?,
        @SerializedName("altitude") var altitude: String?,
        @SerializedName("reason") var reason: String?

    )
}
