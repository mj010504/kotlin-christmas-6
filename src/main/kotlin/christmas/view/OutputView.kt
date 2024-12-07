package christmas.view

import christmas.domain.Order

object OutputView {
    private const val WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."
    private const val PREVIEW_EVENT_SCRIPT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
    private const val ORDER_MENU = "<주문 메뉴>"

    fun printWelcome() {
        println(WELCOME_MESSAGE)
    }

    fun printPreviewEventScript(day : Int) {
        println(PREVIEW_EVENT_SCRIPT.format(day))
    }

    fun printOrder(orders : List<Order>) {
        println(ORDER_MENU)
        orders.forEach { order ->
            println("${order.menu.menuName} ${order.quantity}개")
        }
    }
}