package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.model.Genre
import com.keetr.snac.core.network.models.GenreApiModel

internal fun GenreApiModel.toGenre() = Genre(id, name)
internal fun List<GenreApiModel>.toGenres() = map { genreApiModel -> genreApiModel.toGenre() }