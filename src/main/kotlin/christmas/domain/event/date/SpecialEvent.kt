package christmas.domain.event.date

import christmas.domain.VisitDay

class SpecialEvent(override val visitDay: VisitDay, override val quantity: Int) : DateEvent() {
    override val name = "특별 할인"

    override fun getDiscountAmount(): Int {
        if (eventDays.contains(visitDay.day)) return 1000
        return 0
    }

    companion object {
        private val eventDays: List<Int> = listOf(3, 10, 17, 24, 25, 31)
    }

}