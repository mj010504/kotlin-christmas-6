package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.constants.Menu
import christmas.constants.ORDER_FORMAT
import christmas.domain.Order
import christmas.domain.VisitDay
import christmas.utils.Validator


object InputView {
    private const val VISIT_DAY_SCRIPT = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
    private const val ORDER_SCRIPT = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"

    fun getVisitedDay(): VisitDay {
            try {
                println(VISIT_DAY_SCRIPT)
                val visitDay = Console.readLine()
                Validator.validateVisitDayType(visitDay)
                return VisitDay(visitDay.toInt())
            } catch (e: Exception) {
                println(e.message)
            }

        return VisitDay(1)
    }

    fun getOrder(): List<Order> {
            try {
                println(ORDER_SCRIPT)
                val orderInput = Console.readLine().split(",")
                Validator.validateOrder(orderInput)

                val orders = mutableListOf<Order>()
                orderInput.forEach { order ->
                    val regex = ORDER_FORMAT.toRegex()
                    val matchResult = regex.matchEntire(order)
                    val name = matchResult!!.groupValues[1]
                    val quantity = matchResult.groupValues[2].toInt()
                    orders.add(Order(Menu.convertToMenu(name), quantity))
                }
                return orders

            } catch (e: Exception) {
                println(e.message)
            }
            return listOf()
        }
}