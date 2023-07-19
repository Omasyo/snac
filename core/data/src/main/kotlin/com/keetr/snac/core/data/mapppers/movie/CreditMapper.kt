package com.keetr.snac.core.data.mapppers.movie

import com.keetr.snac.core.data.mapppers.formatTo1dp
import com.keetr.snac.core.model.Credit
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.people.models.MovieActingCreditApiModel
import com.keetr.snac.core.network.people.models.MovieOtherCreditApiModel
import com.keetr.snac.core.network.people.models.TvActingCreditApiModel
import com.keetr.snac.core.network.people.models.TvOtherCreditApiModel

internal fun MovieActingCreditApiModel.toCredit() = Credit(
    id = creditId,
    showId = id,
    role = character,
    title = title,
    rating = voteAverage.formatTo1dp(),
    posterUrl = Api.BasePosterPath + posterPath,
    showType = ShowType.Movie,
    relevance = 0.0,
)

internal fun MovieOtherCreditApiModel.toCredit() = Credit(
    id = creditId,
    showId = id,
    role = job,
    title = title,
    rating = voteAverage.formatTo1dp(),
    posterUrl = Api.BasePosterPath + posterPath,
    showType = ShowType.Movie,
    relevance = 0.0
)

internal fun TvActingCreditApiModel.toCredit() = Credit(
    id = creditId,
    showId = id,
    role = character,
    title = name,
    rating = voteAverage.formatTo1dp(),
    posterUrl = Api.BasePosterPath + posterPath,
    showType = ShowType.Tv,
    relevance = 0.0,
)

internal fun TvOtherCreditApiModel.toCredit() = Credit(
    id = creditId,
    showId = id,
    role = job,
    title = name,
    rating = voteAverage.formatTo1dp(),
    posterUrl = Api.BasePosterPath + posterPath,
    showType = ShowType.Tv,
    relevance = 0.0,
)