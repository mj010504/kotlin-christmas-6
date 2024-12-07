package christmas.view

import christmas.constants.Menu
import christmas.domain.Order
import java.text.NumberFormat
import java.util.Locale

object OutputView {
    private const val WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
    private const val PREVIEW_EVENT_SCRIPT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"

    private const val NONE = "없음"

    private const val ORDER_MENU = "<주문 메뉴>"
    private const val TOTAL_ORDER_PRICE = "<할인 전 총주문 금액>"
    private const val PRESENTATION_MENU = "<증정 메뉴>"
    private const val DISCOUNT_HISTORY = "<혜택 내역>"

    fun printWelcome() {
        println(WELCOME_MESSAGE)
    }

    fun printPreviewEventScript(day : Int) {
        println(PREVIEW_EVENT_SCRIPT.format(day))
        println()
    }

    fun printOrder(orders : List<Order>) {
        println(ORDER_MENU)
        orders.forEach { order ->
            println("${order.menu.menuName} ${order.quantity}개")
        }
        println()
    }

    fun printTotalOrderPrice(totalOrderPrice : Int) {
        println(TOTAL_ORDER_PRICE)
        println("${formatNumber(totalOrderPrice)}원")
        println()
    }

    fun printPresentatin(presentations : Map<Menu, Int>) {
        println(PRESENTATION_MENU)
        if(presentations.isEmpty()) println(NONE)
        presentations.forEach { presentation ->
            println("${presentation.key.menuName} ${presentation.value}개")
        }
        println()
    }

    fun printDiscountHistory(discounts : Map<String, Int>) {
        println(DISCOUNT_HISTORY)
        if(discounts.isEmpty()) println(NONE)
        discounts.forEach { discount ->
            println("${discount.key}: -${formatNumber(discount.value)}")
        }
        println()
    }

    private fun formatNumber(number: Int): String {
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        return numberFormat.format(number)
    }
}