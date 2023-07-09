package com.quitr.snac.feature.search.fake

import kotlin.random.Random

val FakeResults = List(40) {
    if(Random.nextInt(11) % 3 == 0) FakePerson else FakeShow
}