package com.quitr.snac.core.network.tv.models


import com.quitr.snac.core.network.models.GenreApiModel
import com.quitr.snac.core.network.models.ProductionCompanyApiModel
import com.quitr.snac.core.network.models.ProductionCountryApiModel
import com.quitr.snac.core.network.models.SpokenLanguageApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvDetailsApiModel(
    @SerialName("adult") val adult: Boolean,
    @SerialName("backdrop_path") val backdropPath: String,
    @SerialName("created_by") val createdBy: List<CreatedByApiModel>,
    @SerialName("aggregate_credits") val credits: CastCrewApiModel,
    @SerialName("episode_run_time") val episodeRunTime: List<Int>,
    @SerialName("first_air_date") val firstAirDate: String,
    @SerialName("genres") val genres: List<GenreApiModel>,
    @SerialName("homepage") val homepage: String,
    @SerialName("id") val id: Int,
    @SerialName("in_production") val inProduction: Boolean,
    @SerialName("keywords") val keywords: KeywordsApiModel,
    @SerialName("languages") val languages: List<String>,
    @SerialName("last_air_date") val lastAirDate: String,
    @SerialName("last_episode_to_air") val lastEpisodeToAir: EpisodeApiModel,
    @SerialName("name") val name: String,
    @SerialName("networks") val networks: List<NetworkApiModel>,
    @SerialName("next_episode_to_air") val nextEpisodeToAir: EpisodeApiModel?,
    @SerialName("number_of_episodes") val numberOfEpisodes: Int,
    @SerialName("number_of_seasons") val numberOfSeasons: Int,
    @SerialName("origin_country") val originCountry: List<String>,
    @SerialName("original_language") val originalLanguage: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("overview") val overview: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("production_companies") val productionCompanies: List<ProductionCompanyApiModel>,
    @SerialName("production_countries") val productionCountries: List<ProductionCountryApiModel>,
    @SerialName("recommendations") val recommendations: RecommendationsApiModel,
    @SerialName("seasons") val seasons: List<SeasonApiModel>,
    @SerialName("similar") val similar: TvListApiModel,
    @SerialName("spoken_languages") val spokenLanguages: List<SpokenLanguageApiModel>,
    @SerialName("status") val status: String,
    @SerialName("tagline") val tagline: String,
    @SerialName("type") val type: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)