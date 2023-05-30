package com.kadirbulut.spacexfan.domain.datasource.remote

import com.kadirbulut.spacexfan.data.model.response.LaunchResponseModel
import com.kadirbulut.spacexfan.data.model.response.RocketResponseModel

interface SpaceXRemoteDataSource {
    suspend fun getRockets(): List<RocketResponseModel>
    suspend fun getRocketWithId(rocketId: String): RocketResponseModel
    suspend fun getLaunches(): List<LaunchResponseModel>
    suspend fun getLaunchWithId(launchId: String): LaunchResponseModel
}
