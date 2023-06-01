package com.kadirbulut.spacexfan.domain.repository

import com.kadirbulut.spacexfan.data.model.response.LaunchResponseModel
import com.kadirbulut.spacexfan.data.model.response.RocketResponseModel

interface SpaceXRepository {
    /*
     * remote repository func. declarations for rockets
     */
    suspend fun getRockets(): List<RocketResponseModel>
    suspend fun getRocketWithId(rocketId: String): RocketResponseModel

    /*
     * remote repository func. declarations for launches
     */
    suspend fun getLaunches(): List<LaunchResponseModel>
    suspend fun getLaunchWithId(launchId: String): LaunchResponseModel
}
