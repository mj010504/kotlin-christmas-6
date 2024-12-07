package christmas.domain.event.badge

import christmas.constants.Badge

class BadgeEvent(private val totalOrderPrice: Int) {
    fun presentBadge(): Badge? {
        val badges = Badge.entries.sortedByDescending { it.priceCondition }
        badges.forEach { badge ->
            if(totalOrderPrice >= badge.priceCondition) return badge
        }
        return null
    }
}