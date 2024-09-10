package com.example.alexstarter.data.locale.series

import com.example.alexstarter.data.remote.di.RemoteModule.Companion.IMAGE_BASE_URL
import com.example.alexstarter.data.remote.series.dto.SeriesDto
import com.example.alexstarter.domain.model.Series

fun SeriesDto.toSeriesEntity(
): SeriesEntity {
    return SeriesEntity(
        id = id,
        name = name,
        image = IMAGE_BASE_URL + poster_path,
        overview = description,
    )
}

fun SeriesEntity.toSeries(
): Series {
    return Series(
        id = id,
        title = name,
        description = overview ?: "",
        image = IMAGE_BASE_URL + image
    )
}