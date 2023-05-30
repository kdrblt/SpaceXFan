package com.kadirbulut.spacexfan.domain.usecase.remote

import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto
import com.kadirbulut.spacexfan.domain.mapper.toDomainModel
import com.kadirbulut.spacexfan.domain.repository.SpaceXRepository
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

class GetRocketsUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    suspend operator fun invoke(): CallBack<List<RocketModelDto>> {
        return try {
            CallBack.OnLoading
            CallBack.OnSuccess(spaceXRepository.getRockets().toDomainModel())
        } catch (e: HttpException) {
            CallBack.OnError(e)
        } catch (e: IOException) {
            CallBack.OnError(e)
        } catch (e: Exception) {
            CallBack.OnError(e)
        }
    }
}
