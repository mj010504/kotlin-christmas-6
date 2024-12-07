package christmas.domain.event.presentation

import christmas.constants.Menu
import jdk.internal.net.http.common.Pair.pair

class ShampeinEvent(override val totalOrderPrice: Int) : PresentationEvent() {
    override fun present(): Pair<Menu, Int>? {
        if (totalOrderPrice >= MIN_REQUIRED_PRICE) return Pair(Menu.SHAMPEIN, QUANTITY)
        return null
    }

    companion object {
        private const val MIN_REQUIRED_PRICE = 120000
        private const val QUANTITY = 1
    }
}