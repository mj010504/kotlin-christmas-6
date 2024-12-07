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
        val dateEvents = getDateEvents(visitDay)
        val discounts = applyDateEvents(dateEvents)
    }

    private fun getDateEvents(visitDay: VisitDay): List<DateEvent> = listOf(
        WeekDayEvent(visitDay),
        HolidayEvent(visitDay),
        DdayEvent(visitDay),
        SpecialEvent(visitDay)
    )

    private fun applyDateEvents(dateEvents: List<DateEvent>): Map<String, Int> {
        val discounts = mutableMapOf<String, Int>()
        dateEvents.forEach { event ->
            val discountAmount = event.getDiscountAmount()
            if (discountAmount > 0) discounts.put(event.name, discountAmount)
        }
        return discounts
    }
}