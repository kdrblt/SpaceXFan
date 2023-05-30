package com.kadirbulut.spacexfan.domain.mapper

import com.kadirbulut.spacexfan.data.model.response.RocketResponseModel
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto

fun RocketResponseModel.toDomainModel() = RocketModelDto(
    id,
    name
)

// for list
fun List<RocketResponseModel>?.toDomainModel() =
    this?.map { it.toDomainModel() } ?: arrayListOf()
