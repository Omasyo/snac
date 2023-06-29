package com.quitr.snac.core.model

enum class Gender {
    NotSpecified,
    Female,
    Male,
    NonBinary;

    companion object {
        fun from(id: Int) = when (id) {
            1 -> NotSpecified
            2 -> Female
            3 -> Male
            4 -> NonBinary
            else -> throw IllegalArgumentException()
        }
    }
}