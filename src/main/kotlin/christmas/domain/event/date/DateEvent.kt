package christmas.domain.event.date

import christmas.domain.VisitDay

abstract class DateEvent() {
    abstract val visitDay: VisitDay
    abstract fun getDiscountAmount() : Int
}