package com.kadirbulut.spacexfan.domain.usecase.remote

import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.domain.mapper.toDomainModel
import com.kadirbulut.spacexfan.domain.repository.SpaceXRepository
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

class GetRocketWithIdUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    data class Params(
        val rocketId: String // rocket id for specific rocket
    )
    suspend operator fun invoke(parameters: Params): CallBack<RocketModelDto> {
        return try {
            CallBack.OnLoading
            CallBack.OnSuccess(
                spaceXRepository.getRocketWithId(parameters.rocketId).toDomainModel()
            )
        } catch (e: HttpException) {
            CallBack.OnError(e)
        } catch (e: IOException) {
            CallBack.OnError(e)
        } catch (e: Exception) {
            CallBack.OnError(e)
        }
    }
}
