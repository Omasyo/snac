package com.quitr.snac.core.model

import org.intellij.lang.annotations.Language

data class Movie(
    val id: Int,
    val backDropUrl: String,
    val budget: Int,
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
    val revenue: Int,
    val runtime: Int, //TODO: or maybe use a time type?
    val similar: List<Show>,
    val spokenLanguages: List<String>,
    val status: String, //TODO should definitely be enum
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    )
/** Not included
 *  adult
 *  belongsToCollection
 *  video
 */
