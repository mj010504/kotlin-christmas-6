package christmas.domain.event.presentation

import christmas.constants.Menu

class ShampeinEvent(override val totalOrderPrice: Int) : PresentationEvent() {
    override fun present(): Map<Menu, Int>? {
        if (totalOrderPrice >= MIN_REQUIRED_PRICE) return mapOf(Menu.SHAMPEIN to QUANTITY)
        return null
    }

    companion object {
        private const val MIN_REQUIRED_PRICE = 120000
        private const val QUANTITY = 1
    }
}