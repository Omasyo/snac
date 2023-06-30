package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Credit
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.people.models.MovieActingCreditApiModel
import com.quitr.snac.core.network.people.models.MovieOtherCreditApiModel
import com.quitr.snac.core.network.people.models.TvActingCreditApiModel
import com.quitr.snac.core.network.people.models.TvOtherCreditApiModel

internal fun MovieActingCreditApiModel.toCredit() = Credit(
    id = creditId,
    showId = id,
    role = character,
    title = title,
    rating = voteAverage.toString(),
    posterUrl = Api.BasePosterPath + posterPath,
    showType = ShowType.Movie,
    relevance = 0.0,
)

internal fun MovieOtherCreditApiModel.toCredit() = Credit(
    id = creditId,
    showId = id,
    role = job,
    title = title,
    rating = voteAverage.toString(),
    posterUrl = Api.BasePosterPath + posterPath,
    showType = ShowType.Movie,
    relevance = 0.0
)

internal fun TvActingCreditApiModel.toCredit() = Credit(
    id = creditId,
    showId = id,
    role = character,
    title = name,
    rating = voteAverage.toString(),
    posterUrl = Api.BasePosterPath + posterPath,
    showType = ShowType.Movie,
    relevance = 0.0,
)

internal fun TvOtherCreditApiModel.toCredit() = Credit(
    id = creditId,
    showId = id,
    role = job,
    title = name,
    rating = voteAverage.toString(),
    posterUrl = Api.BasePosterPath + posterPath,
    showType = ShowType.Movie,
    relevance = 0.0,
)