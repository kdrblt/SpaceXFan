package com.kadirbulut.spacexfan.domain.usecase.remote

import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.data.model.response.LaunchResponseModel
import com.kadirbulut.spacexfan.domain.repository.SpaceXRepository
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

class GetLaunchWithIdUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    data class Params(
        val launchId: String // launch id for specific launch
    )
    suspend operator fun invoke(parameters: Params): CallBack<LaunchResponseModel> {
        return try {
            CallBack.OnLoading
            CallBack.OnSuccess(spaceXRepository.getLaunchWithId(parameters.launchId))
        } catch (e: HttpException) {
            CallBack.OnError(e)
        } catch (e: IOException) {
            CallBack.OnError(e)
        } catch (e: Exception) {
            CallBack.OnError(e)
        }
    }
}
