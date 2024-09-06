package com.example.alexstarter.data.locale.series

import com.example.alexstarter.data.remote.di.RemoteModule.Companion.IMAGE_BASE_URL
import com.example.alexstarter.data.remote.series.dto.SeriesDto
import com.example.alexstarter.domain.model.Series

fun SeriesDto.toSeriesEntity(
): SeriesEntity {
    return SeriesEntity(
        id = id,
        //title = title,
        overview = description,
        image = IMAGE_BASE_URL + poster_path,
    )
}

fun SeriesEntity.toSeries(
): Series {
    return Series(
        id = id,
        //title = title,
        description = overview ?: "",
        image = IMAGE_BASE_URL + image
    )
}