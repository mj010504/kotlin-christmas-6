package christmas.domain.event.date

import christmas.domain.VisitDay
import java.time.DayOfWeek
import java.time.LocalDate

class HolidayEvent(override val visitDay: VisitDay, val desertCount : Int) : DateEvent()  {
    override val name = "휴일 할인"

    override fun getDiscountAmount(): Int {
        val date = LocalDate.of(2023, 12, visitDay.day)
        val dayOfWeek = date.dayOfWeek
        if(dayOfWeek in DayOfWeek.SATURDAY..DayOfWeek.SUNDAY) return DISCOUNT_AMOUNT * desertCount
        return 0
    }

    companion object {
        private const val DISCOUNT_AMOUNT = 2023
    }

}