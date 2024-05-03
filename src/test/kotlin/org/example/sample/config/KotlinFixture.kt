package org.example.sample.config

import com.appmattus.kotlinfixture.kotlinFixture
import org.example.sample.domain.Guide
import org.example.sample.domain.GuideType

val fixture = kotlinFixture()
val guideFixture = fixture<Guide> {
    property(Guide::title) { faker.random.randomString(10, false) }
    property(Guide::author) { faker.name.name() }
    property(Guide::type) { GuideType.TEAM }
    property(Guide::isEnable) { true }
}