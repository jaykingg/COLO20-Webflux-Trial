package org.example.sample.integration.guidecontroller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.example.sample.config.guideFixture
import org.example.sample.domain.GuideType
import org.example.sample.repository.GuideRepository
import org.example.sample.view.GuideView
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest

@ActiveProfiles("test")
@AutoConfigureWebTestClient
class GetAllGuideIT(
    private val webTestClient: WebTestClient,
    private val guideRepository: GuideRepository
) : BehaviorSpec({
    val endpoint = "/api/guide"
    val request = webTestClient.get().uri(endpoint)
    val repeatTimes = 30

    beforeSpec {
        repeat(repeatTimes) {
            guideRepository.save(guideFixture).also { println("saved::: $it") }
        }
    }
    afterSpec { guideRepository.deleteAll() }

    Given("Guide 전체 조회") {
        When("정상인 경우") {
            Then("Response 200 - Flow<GuideView>") {
                val result = request.exchange()
                    .expectStatus().isOk
                    .expectBody<List<GuideView>>()
                    .shouldNotBeNull()
                    .returnResult()
                    .responseBody!!

                result.size.shouldBe(repeatTimes)
                result.first().type.shouldBeInstanceOf<GuideType>()
                result.first().isEnable.shouldBeInstanceOf<Boolean>()
            }
        }
    }
})