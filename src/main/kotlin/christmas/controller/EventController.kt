package christmas.controller

import christmas.constants.Badge
import christmas.constants.Menu
import christmas.domain.Order
import christmas.domain.RestaurantMenu
import christmas.domain.VisitDay
import christmas.domain.event.badge.BadgeEvent
import christmas.domain.event.date.DateEvent
import christmas.domain.event.date.DdayEvent
import christmas.domain.event.date.HolidayEvent
import christmas.domain.event.date.SpecialEvent
import christmas.domain.event.date.WeekDayEvent
import christmas.domain.event.presentation.PresentationEvent
import christmas.domain.event.presentation.ShampeinEvent
import christmas.view.InputView
import christmas.view.InputView.getOrder
import christmas.view.InputView.getVisitedDay
import christmas.view.OutputView
import christmas.view.OutputView.printDiscountHistory
import christmas.view.OutputView.printEventBadge
import christmas.view.OutputView.printOrder
import christmas.view.OutputView.printPresentatin
import christmas.view.OutputView.printPreviewEventScript
import christmas.view.OutputView.printTotalDiscountAmount
import christmas.view.OutputView.printTotalOrderPrice
import christmas.view.OutputView.printTotalPurchaseAmount
import christmas.view.OutputView.printWelcome

class EventController {

    fun run() {
        printWelcome()
        val visitDay = getVisitedDay()
        val orders = getOrder()

        val totalOrderPrice = orders.sumOf { order ->
            order.getTotalPrice()
        }

        // 구매 날짜 관련 할인 이벤트
        val dateEvents = getDateEvents(visitDay, orders)
        val discounts: MutableMap<String, Int> = applyDateEvents(dateEvents)

        // 증정 이벤트
        val prresentationEvents = getPresentationEvents(totalOrderPrice)
        val presentations = applyPresentationEvents(prresentationEvents)
        val totalPresentationPrice = presentations.entries.sumOf { presentation ->
            val menu = presentation.key
            menu.price * presentation.value
        }
        if (totalPresentationPrice > 0) discounts.put("증정 이벤트", totalPresentationPrice)

        // 뱃지 증정 이벤트
        val badge = BadgeEvent().getBadge(totalOrderPrice)

        val totalDiscountAmount = discounts.entries.sumOf { it.value }
        val totalPurchaseAmount = totalOrderPrice - totalDiscountAmount + totalPresentationPrice

        printPreviewEventScript(visitDay.day)
        printOrder(orders)
        printTotalOrderPrice(totalOrderPrice)
        printPresentatin(presentations)
        printDiscountHistory(discounts)
        printTotalDiscountAmount(totalDiscountAmount)
        printTotalPurchaseAmount(totalPurchaseAmount)
        printEventBadge(badge)
    }

    private fun getDateEvents(visitDay: VisitDay, orders: List<Order>): List<DateEvent> {
        val mainCount = orders.count { order ->
            order.isMain()
        }

        val desertCount = orders.count { order ->
            order.isDesert()
        }

        return listOf(
            WeekDayEvent(visitDay, desertCount),
            HolidayEvent(visitDay, mainCount),
            DdayEvent(visitDay),
            SpecialEvent(visitDay)
        )
    }

    private fun getPresentationEvents(totalOrderPrice: Int): List<PresentationEvent> = listOf(
        ShampeinEvent(totalOrderPrice)
    )

    private fun applyDateEvents(dateEvents: List<DateEvent>): MutableMap<String, Int> {
        val discounts = mutableMapOf<String, Int>()
        dateEvents.forEach { event ->
            val discountAmount = event.getDiscountAmount()
            if (discountAmount > 0) discounts.put(event.name, discountAmount)
        }
        return discounts
    }

    private fun applyPresentationEvents(presentationEvents: List<PresentationEvent>): Map<Menu, Int> {
        val presentations = mutableMapOf<Menu, Int>()
        presentationEvents.forEach { event ->
            val presentation = event.present()
            if (presentation != null) presentations.put(presentation.first, presentation.second)
        }
        return presentations
    }
}