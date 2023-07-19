package com.keetr.snac.core.model

data class Movie(
    val id: Int,
    val backDropUrl: String,
    val budget: String,
    val cast: List<Person>,
    val crew: List<Person>,
    val genres: List<Genre>, //TODO: find a better type enums?(could interface work) get from db? who knows
    val homePageUrl: String,
    val imdbId: String,
    val keywords: List<Keyword>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterUrl: String,
    val productionCompanies: List<String>,
    val productionCountries: List<String>,
    val recommendations: List<Show>,
    val releaseDate: String,
    val revenue: String,
    val runtime: Int, //TODO: or maybe use a time type?
    val similar: List<Show>,
    val spokenLanguages: List<String>,
    val status: String, //TODO should definitely be enum
    val tagline: String,
    val title: String,
    val voteAverage: String,
    val voteCount: Int,
    )
/** Not included
 *  adult
 *  belongsToCollection
 *  video
 */
