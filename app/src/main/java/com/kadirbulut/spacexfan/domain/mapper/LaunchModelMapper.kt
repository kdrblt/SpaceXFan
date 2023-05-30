package com.kadirbulut.spacexfan.domain.mapper

import com.kadirbulut.spacexfan.data.model.response.LaunchResponseModel
import com.kadirbulut.spacexfan.domain.dto.LaunchModelDto

fun LaunchResponseModel.toDomainModel() = LaunchModelDto(
    id,
    name
)

// for list
fun List<LaunchResponseModel>?.toDomainModel() =
    this?.map { it.toDomainModel() } ?: arrayListOf()
