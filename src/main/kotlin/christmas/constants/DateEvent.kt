package christmas.constants

import christmas.domain.VisitDay

abstract class DateEvent() {
    abstract val visitDay: VisitDay
    abstract fun getDiscountAmount() : Int
}