package com.quitr.snac.core.network.movie.models


import com.quitr.snac.core.network.models.GenreApiModel
import com.quitr.snac.core.network.models.KeywordsApiModel
import com.quitr.snac.core.network.models.ProductionCompanyApiModel
import com.quitr.snac.core.network.models.ProductionCountryApiModel
import com.quitr.snac.core.network.models.SpokenLanguageApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsApiModel(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String?,
//    @SerialName("belongs_to_collection") val belongsToCollection: Any,
    @SerialName("budget") val budget: Int,
    @SerialName("credits") val credits: CastCrewApiModel,
    @SerialName("genres") val genres: List<GenreApiModel>,
    @SerialName("homepage") val homepage: String,
    @SerialName("id") val id: Int,
    @SerialName("imdb_id") val imdbId: String?,
    @SerialName("keywords") val keywords: KeywordsApiModel,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("overview") val overview: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyApiModel>,
    @SerialName("production_countries") val productionCountries: List<ProductionCountryApiModel>,
    @SerialName("recommendations") val recommendations: RecommendationsApiModel,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("revenue") val revenue: Int,
    @SerialName("runtime") val runtime: Int,
    @SerialName("similar") val similar: MovieListApiModel,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguageApiModel>,
    @SerialName("status") val status: String,
    @SerialName("tagline") val tagline: String,
    @SerialName("title") val title: String,
    @SerialName("video") val video: Boolean,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)