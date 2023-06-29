package com.quitr.snac.feature.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.quitr.snac.core.data.movie.MovieRepository
import com.quitr.snac.core.data.tv.TvRepository
import com.quitr.snac.core.model.SectionType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecommendationsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    movieRepository: MovieRepository,
) : ViewModel() {

    val movieId: Int = checkNotNull(savedStateHandle.get<Int>(RecommendationsRoute.movieId))

    val shows  = movieRepository.getRecommendationStream(movieId)
}