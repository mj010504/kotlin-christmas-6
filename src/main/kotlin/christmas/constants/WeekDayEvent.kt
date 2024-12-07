package christmas.constants

import christmas.domain.VisitDay
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class WeekDayEvent(override val visitDay: VisitDay) : DateEvent()  {

    override fun getDiscountAmount(): Int {
        val date = LocalDate.of(2023, 12, visitDay.day)
        val dayOfWeek = date.dayOfWeek
        if(dayOfWeek in DayOfWeek.MONDAY..DayOfWeek.FRIDAY) return DISCOUNT_AMOUNT
        return 0
    }

    companion object {
        private const val DISCOUNT_AMOUNT = 2023
    }

}