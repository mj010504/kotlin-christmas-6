package christmas.domain.event.presentation

import christmas.constants.Menu

abstract class PresentationEvent {
    abstract val totalOrderPrice : Int
    abstract fun present() : Menu?
}