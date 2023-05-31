package com.kadirbulut.spacexfan.domain.mapper

import com.kadirbulut.spacexfan.data.model.response.LaunchResponseModel
import com.kadirbulut.spacexfan.domain.dto.LaunchModelDto

fun LaunchResponseModel.toDomainModel() = LaunchModelDto(
    id = id,
    name = name,
    date_utc = dateUtc,
    flightNumber = flightNumber,
    links = links?.toDomainModel()
)

fun LaunchResponseModel.Reddit.toDomainModel() = LaunchModelDto.Reddit(
    campaign, launch, media, recovery
)

fun LaunchResponseModel.Flickr.toDomainModel() = LaunchModelDto.Flickr(
    small, original
)

fun LaunchResponseModel.Patch.toDomainModel() = LaunchModelDto.Patch(
    small, large
)

fun LaunchResponseModel.Links.toDomainModel() = LaunchModelDto.Links(
    patch = patch?.toDomainModel(),
    reddit = reddit?.toDomainModel(),
    flickr = flickr?.toDomainModel(),
    presskit, webcast, youtubeId, article, wikipedia
)

// for list
fun List<LaunchResponseModel>?.toDomainModel() =
    this?.map { it.toDomainModel() } ?: arrayListOf()
