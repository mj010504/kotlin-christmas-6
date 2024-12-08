package christmas.domain.event.date

import christmas.domain.VisitDay
import java.time.DayOfWeek
import java.time.LocalDate

class WeekDayEvent(override val visitDay: VisitDay, val mainCount : Int) : DateEvent()  {
    override val name = "평일 할인"

    override fun getDiscountAmount(): Int {
        val date = LocalDate.of(2023, 12, visitDay.day)
        val dayOfWeek = date.dayOfWeek
        if(dayOfWeek in DayOfWeek.MONDAY..DayOfWeek.FRIDAY) return DISCOUNT_AMOUNT * mainCount
        return 0
    }

    companion object {
        private const val DISCOUNT_AMOUNT = 2023
    }

}