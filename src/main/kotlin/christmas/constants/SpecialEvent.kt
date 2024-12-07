package christmas.constants

import christmas.domain.VisitDay
import kotlin.math.absoluteValue

class SpecialEvent(override val visitDay: VisitDay) : DateEvent() {
    override fun getDiscountAmount(): Int {
        if (eventDays.contains(visitDay.day)) return 1000
        return 0
    }

    companion object {
        private val eventDays: List<Int> = listOf(3, 10, 17, 24, 25, 31)
    }

}