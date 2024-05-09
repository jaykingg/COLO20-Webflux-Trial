package org.example.sample.domain

import org.example.sample.core.exceptions.BaseError

enum class GuideError(override val message: String) : BaseError {
    TEST_GUIDE_SAMPLE_ERROR("샘플 에러 입니다.")
}