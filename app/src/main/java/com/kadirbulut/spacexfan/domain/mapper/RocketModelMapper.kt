package com.kadirbulut.spacexfan.domain.mapper

import com.kadirbulut.spacexfan.data.model.response.RocketResponseModel
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto

fun RocketResponseModel.toDomainModel() = RocketModelDto(
    id = id,
    name = name,
    flickrImages = flickrImages,
    description = description
)

// for list
fun List<RocketResponseModel>?.toDomainModel() =
    this?.map { it.toDomainModel() } ?: arrayListOf()
