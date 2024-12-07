package christmas.domain.event.date

import christmas.domain.VisitDay

abstract class DateEvent() {
    abstract val visitDay: VisitDay
    abstract val quantity : Int
    abstract val name : String
    abstract fun getDiscountAmount() : Int
}