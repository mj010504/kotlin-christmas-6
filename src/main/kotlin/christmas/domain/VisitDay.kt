package christmas.domain

import christmas.utils.Validator

class VisitDay(val day : Int) {
    init {
        validateDay()
    }

    private fun validateDay() {
        require(day >= MIN_DAY && day <= MAX_DAY) {
            Validator.getErrorMessage(INVALID_DAY)
        }
    }

    companion object {
        private const val MIN_DAY = 1
        private const val MAX_DAY = 31
        private const val INVALID_DAY = "유효하지 않은 날짜입니다."
    }

}