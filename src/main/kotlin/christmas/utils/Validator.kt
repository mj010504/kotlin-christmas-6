package christmas.utils


import christmas.constants.Menu
import christmas.constants.ORDER_FORMAT
import christmas.domain.Order
import christmas.domain.Order.Companion

object Validator {
    private const val ERROR_LABLE = "[ERROR]"
    private const val RETRY_INPUT = "다시 입력해 주세요."
    private const val INVALID_DAY = "유효하지 않은 날짜입니다."
    private const val INVALID_ORDER = "유효하지 않은 주문입니다."


    fun validateVisitDayType(visitDay: String) {
        visitDay.toIntOrNull() ?: throw IllegalArgumentException(getErrorMessage(INVALID_DAY))
    }

    fun validateOrder(orders: List<String>) {
        val names = mutableListOf<String>()
        orders.forEach { order ->
            val regex = ORDER_FORMAT.toRegex()
            validateOrderFormat(regex, order)
            val matchResult = regex.matchEntire(order)
            val name = matchResult!!.groupValues[1]
            validateMenu(name)
            names.add(name)
            verfiyNoDuplicates(names)
        }

    }

    private fun validateOrderFormat(regex: Regex, order: String) {
        require(regex.matches(order)) {
            getErrorMessage(INVALID_ORDER)
        }
    }

    private fun verfiyNoDuplicates(names: MutableList<String>) {
        require(names.size == names.toSet().size) {
            getErrorMessage(INVALID_ORDER)
        }
    }

    private fun validateMenu(name: String) {
        require(Menu.validateMenu(name)) {
            getErrorMessage(INVALID_ORDER)
        }
    }

    fun getErrorMessage(message: String): String = "$ERROR_LABLE $message $RETRY_INPUT"

}