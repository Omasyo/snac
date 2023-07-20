package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.model.Person
import com.keetr.snac.core.model.Show
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.search.models.SearchMovieApiModel
import com.keetr.snac.core.network.search.models.SearchPersonApiModel
import com.keetr.snac.core.network.search.models.SearchResultApiModel
import com.keetr.snac.core.network.search.models.SearchTvApiModel

fun SearchResultApiModel.toSearchResult(): Any = when (this) {
    is SearchMovieApiModel -> Show(
        id = id,
        title = title,
        rating = voteAverage.formatTo1dp(),
        posterUrl = Api.BasePosterPath + posterPath,
        showType = ShowType.Movie
    )

    is SearchPersonApiModel -> Person(
        id = id,
        name = name,
        role = knownForDepartment ?: "None",
        photoUrl = Api.BaseProfilePath + profilePath

    )

    is SearchTvApiModel -> Show(
        id = id,
        title = name,
        rating = voteAverage.formatTo1dp(),
        posterUrl = Api.BasePosterPath + posterPath,
        showType = ShowType.Tv
    )
}

fun List<SearchResultApiModel>.toSearchResults() =
    map { searchResultApiModel -> searchResultApiModel.toSearchResult() }