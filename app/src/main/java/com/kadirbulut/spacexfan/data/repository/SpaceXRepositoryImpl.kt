package com.kadirbulut.spacexfan.data.repository

import com.kadirbulut.spacexfan.data.model.response.LaunchResponseModel
import com.kadirbulut.spacexfan.data.model.response.RocketResponseModel
import com.kadirbulut.spacexfan.domain.datasource.remote.SpaceXRemoteDataSource
import com.kadirbulut.spacexfan.domain.repository.SpaceXRepository
import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor(
    private val spaceXRemoteDataSource: SpaceXRemoteDataSource
) : SpaceXRepository {

    // remote repository implementations for rockets
    override suspend fun getRockets(): List<RocketResponseModel> =
        spaceXRemoteDataSource.getRockets()

    override suspend fun getRocketWithId(rocketId: String): RocketResponseModel =
        spaceXRemoteDataSource.getRocketWithId(rocketId)

    // remote repository implementations for launches
    override suspend fun getLaunches(): List<LaunchResponseModel> =
        spaceXRemoteDataSource.getLaunches()

    override suspend fun getLaunchWithId(launchId: String): LaunchResponseModel =
        spaceXRemoteDataSource.getLaunchWithId(launchId)
}
