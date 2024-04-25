package org.example.sample.integration

import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class IntegrationTestSample(
) : BehaviorSpec({

    /**
     *  API 에 사용되는 인증정보를 검증한다.
     */
    Given("인증 검증") {
        When("인증 실패시") {
            Then("Response 401 UNAUTHORIZED") {
                //..
            }
        }
    }

    /**
     * Data Transfer 에 사용되는 payload 를 검증한다.
     */
    Given("payload 검증") {
        When("payload 가 유효하지 않은 경우") {
            Then("Response 400 BAD_REQUEST") {
                //..
            }
        }
    }


    /**
     * Controller 부터 Service 내 Business Logic 을 검증한다.
     */
    Given("비즈니스 로직 검증") {
        When("Fail Fail Case 1") {
            Then("Response 400 BAD_REQUEST") {
                //..
            }
        }

        When("Fail Case 2") {
            And("Fail Case 2-1") {
                Then(" Response 400 BAD_REQUEST") {
                    //..
                }
            }
            And("Fail Case 2-2") {
                Then("Response 400 BAD_REQUEST") {
                    //..
                }
            }
        }

        /**
         * Worst Fail Case
         */
        When("Fail Case 3") {
            And("Fail Case 3-1") {
                // worst, 최소한의 Depth 를 둔다.
                And("Fail Case 3-1-1") {
                    Then("Response 400 BAD_REQUEST") {

                    }
                }
            }
        }


        /**
         * 모두 정상일 경우,
         * Response Object Type 을 검증한다.
         * Data expected in the Response Object 검증한다.
         */
        When("Success Case") {
            Then("Response 204 NO_CONTENT") {
                //...
            }
        }
    }


    Given("Mockk") {
        When("Mockking 이 필요할 경우") {
            Then("CoEvery{} 를 사용하여 정의한다.") {

            }
        }
    }

    Given("외부 API 연동테스트가 필요할 경우") {
        When("외부 API 연동테스트가 필요할 경우 ") {
            Then("정상동작을 정의하여 테스트한다.") {


            }
            Then("비정상 동작을 정의하여 테스트한다.") {

            }
        }
    }
})