package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Genre
import com.quitr.snac.core.network.movie.models.GenreApiModel

internal fun GenreApiModel.toGenre() = Genre(id, name)
internal fun List<GenreApiModel>.toGenres() = map { genreApiModel -> genreApiModel.toGenre() }