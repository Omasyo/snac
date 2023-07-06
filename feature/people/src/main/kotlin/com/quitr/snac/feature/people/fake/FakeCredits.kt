package com.quitr.snac.feature.people.fake

import com.quitr.snac.core.model.Credit
import com.quitr.snac.core.model.ShowType

internal val Credits = List(15) {
    Credit(
        id = "$it",
        role = "FisherMan",
        title = "Call of Hunger",
        rating = "10.0",
        posterUrl = "https://duckduckgo.com/?q=arcu",
        showType = ShowType.Tv,
        relevance = 2.3,
        showId = 6152
    )
}