package christmas.controller

import christmas.constants.Menu
import christmas.domain.RestaurantMenu
import christmas.view.InputView
import christmas.view.InputView.getVisitedDay
import christmas.view.OutputView
import christmas.view.OutputView.printWelcome

class EventController {

    fun run() {
        printWelcome()
        getVisitedDay()
        val restaurantMenu = RestaurantMenu(Menu.entries)
    }
}