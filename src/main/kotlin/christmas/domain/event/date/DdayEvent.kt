package christmas.domain.event.date

import christmas.domain.VisitDay

class DdayEvent(override val visitDay: VisitDay, override val quantity: Int) : DateEvent()  {
    override val name = "크리스마스 디데이 할인"
    override fun getDiscountAmount(): Int {
        if(visitDay.day >= MAX_EVENT_DAY) return 0
        return START_DISCOUNT_AMOUNT + INCREASE_AMOUNT * (visitDay.day - 1)
    }

    companion object {
        private const val MAX_EVENT_DAY = 25
        private const val START_DISCOUNT_AMOUNT = 1000
        private const val INCREASE_AMOUNT = 100

    }

}