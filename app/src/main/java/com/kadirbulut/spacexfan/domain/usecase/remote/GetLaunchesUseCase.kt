package com.kadirbulut.spacexfan.domain.usecase.remote

import com.kadirbulut.spacexfan.common.util.CallBack
import com.kadirbulut.spacexfan.domain.dto.LaunchModelDto
import com.kadirbulut.spacexfan.domain.mapper.toDomainModel
import com.kadirbulut.spacexfan.domain.repository.SpaceXRepository
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

class GetLaunchesUseCase @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) {
    suspend operator fun invoke(): CallBack<List<LaunchModelDto>> {
        return try {
            CallBack.OnLoading
            CallBack.OnSuccess(spaceXRepository.getLaunches().toDomainModel())
        } catch (e: HttpException) {
            CallBack.OnError(e)
        } catch (e: IOException) {
            CallBack.OnError(e)
        } catch (e: Exception) {
            CallBack.OnError(e)
        }
    }
}
