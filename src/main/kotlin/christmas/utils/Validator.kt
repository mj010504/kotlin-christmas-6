package christmas.utils

object Validator {
    private const val ERROR_LABLE = "[ERROR]"
    private const val RETRY_INPUT = "다시 입력해 주세요."
    private const val INVALID_VISIT_DAY = "방문 날짜는 숫자만 입력이 가능합니다."

    fun validateVisitDay (visitDay : String) {
       visitDay.toIntOrNull() ?: throw IllegalArgumentException(INVALID_VISIT_DAY)
    }

    fun getErrorMessage(message: String): String = "$ERROR_LABLE $message $RETRY_INPUT"

}