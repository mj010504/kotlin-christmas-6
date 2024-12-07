package christmas.domain.event.badge

import christmas.constants.Badge

class BadgeEvent {
    fun getBadge(totalOrderPrice : Int): Badge? {
        val badges = Badge.entries.sortedByDescending { it.priceCondition }
        badges.forEach { badge ->
            if(totalOrderPrice >= badge.priceCondition) return badge
        }
        return null
    }
}