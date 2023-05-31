package com.kadirbulut.spacexfan.data.source.remote

import com.kadirbulut.spacexfan.data.model.response.LaunchResponseModel
import com.kadirbulut.spacexfan.data.model.response.RocketResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXServices {
    // get rockets' list
    @GET(ROCKETS)
    suspend fun getRockets(): List<RocketResponseModel>

    // get specific rocket with id
    @GET(ROCKET)
    suspend fun getRocketWithId(@Path(value = "id") rocketId: String): RocketResponseModel

    // get launches' list
    @GET(LAUNCHES)
    suspend fun getLaunches(): List<LaunchResponseModel>

    // get specific launch with id
    @GET(LAUNCH)
    suspend fun getLaunchWithId(@Path(value = "id") launchId: String): LaunchResponseModel

    companion object {
        private const val ROCKETS = "v4/rockets"
        private const val ROCKET = "v4/rockets/{id}"
        private const val LAUNCHES = "v5/launches"
        private const val LAUNCH = "v5/launches/:id"
    }
}
