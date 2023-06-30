package com.quitr.snac.core.model

enum class Gender {
    NotSpecified,
    Female,
    Male,
    NonBinary;

    companion object {
        fun from(id: Int) = when (id) {
            0 -> NotSpecified
            1 -> Female
            2 -> Male
            3 -> NonBinary
            else -> throw IllegalArgumentException("Invalid Gender Id")
        }
    }
}