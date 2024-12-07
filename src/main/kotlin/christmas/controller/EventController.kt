package christmas.controller

import christmas.constants.Menu
import christmas.domain.Order
import christmas.domain.RestaurantMenu
import christmas.domain.VisitDay
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
import christmas.view.OutputView.printWelcome

class EventController {

    fun run() {
        printWelcome()
        val visitDay = getVisitedDay()
        val orders = getOrder()

        val totalOrderPrice = orders.sumOf { order ->
            order.getTotalPrice()
        }

        // 증정 이벤트
        val prresentationEvents = getPresentationEvents(totalOrderPrice)
        val presentations = applyPresentationEvents(prresentationEvents)

        // 구매 날짜 관련 할인 이벤트
        val dateEvents = getDateEvents(visitDay)
        val discounts: Map<String, Int> = applyDateEvents(dateEvents)
    }

    private fun getDateEvents(visitDay: VisitDay): List<DateEvent> = listOf(
        WeekDayEvent(visitDay),
        HolidayEvent(visitDay),
        DdayEvent(visitDay),
        SpecialEvent(visitDay)
    )

    private fun getPresentationEvents(totalOrderPrice: Int): List<PresentationEvent> = listOf(
        ShampeinEvent(totalOrderPrice)
    )

    private fun applyDateEvents(dateEvents: List<DateEvent>): Map<String, Int> {
        val discounts = mutableMapOf<String, Int>()
        dateEvents.forEach { event ->
            val discountAmount = event.getDiscountAmount()
            if (discountAmount > 0) discounts.put(event.name, discountAmount)
        }
        return discounts
    }

    private fun applyPresentationEvents(presentationEvents : List<PresentationEvent>) : Map<Menu, Int> {
        val presentations = mutableMapOf<Menu, Int>()
        presentationEvents.forEach { event ->
            val presentation = event.present()
            if(presentation != null) presentations.put(presentation.first, presentation.second)
        }
        return presentations
    }
}