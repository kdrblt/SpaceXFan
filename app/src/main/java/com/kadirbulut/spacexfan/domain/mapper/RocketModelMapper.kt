package com.kadirbulut.spacexfan.domain.mapper

import com.kadirbulut.spacexfan.data.model.response.RocketResponseModel
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto

/*
 * This data transfer object class was created to use just enough data.
 * Not all respones datas
 */
fun RocketResponseModel.toDomainModel() = RocketModelDto(
    id, name, type, country, company, wikipedia, firstFlight, flickrImages, description,
    height?.toDomainModel(), diameter?.toDomainModel(), mass?.toDomainModel()
)

// for list
fun List<RocketResponseModel>?.toDomainModel() =
    this?.map { it.toDomainModel() } ?: arrayListOf()

fun RocketResponseModel.Height.toDomainModel() = RocketModelDto.Height(
    meters, feet
)

fun RocketResponseModel.Mass.toDomainModel() = RocketModelDto.Mass(
    kg, lb
)
