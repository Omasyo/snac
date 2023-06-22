package com.quitr.snac.core.model

import java.util.Date

data class Movie(
    val id: Int,
    val backDropUrl: String,
    val budget: Int,
    val genres: Int, //TODO: find a better type enums? get from db? who knows
    val homePageUrl: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String,
    val productionCompanies: List<String>,
    val productionCountries: List<String>,
    val releaseDate: Date,
    val revenue: Int,
    val runtime: Int, //TODO: or maybe use a time type?
    val status: String, //TODO should definitely be enum
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    )
/** Not included
 *  adult
 *  belongsToCollection
 *  imdb id?
 */
