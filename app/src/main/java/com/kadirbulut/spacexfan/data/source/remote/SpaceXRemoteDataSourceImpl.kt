package com.kadirbulut.spacexfan.data.source.remote

import com.kadirbulut.spacexfan.data.model.response.LaunchResponseModel
import com.kadirbulut.spacexfan.data.model.response.RocketResponseModel
import com.kadirbulut.spacexfan.domain.datasource.remote.SpaceXRemoteDataSource
import javax.inject.Inject

class SpaceXRemoteDataSourceImpl @Inject constructor(
    private val spaceXServices: SpaceXServices
) : SpaceXRemoteDataSource {

    /*
     * remote data source implementations for rockets
     */
    override suspend fun getRockets(): List<RocketResponseModel> = spaceXServices.getRockets()
    override suspend fun getRocketWithId(rocketId: String): RocketResponseModel =
        spaceXServices.getRocketWithId(rocketId)

    /*
     * remote data source implementations for launches
     */
    override suspend fun getLaunches(): List<LaunchResponseModel> = spaceXServices.getLaunches()
    override suspend fun getLaunchWithId(launchId: String): LaunchResponseModel =
        spaceXServices.getLaunchWithId(launchId)
}
