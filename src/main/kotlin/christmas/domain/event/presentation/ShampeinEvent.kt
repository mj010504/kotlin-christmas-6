package christmas.domain.event.presentation

import christmas.constants.Menu

class ShampeinEvent(override val totalOrderPrice: Int) : PresentationEvent() {
    override fun present(): Menu? {
        if (totalOrderPrice >= MIN_REQUIRED_PRICE) return Menu.SHAMPEIN
        return null
    }

    companion object {
        private const val MIN_REQUIRED_PRICE = 120000
    }
}