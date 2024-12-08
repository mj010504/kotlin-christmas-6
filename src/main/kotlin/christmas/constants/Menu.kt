package christmas.constants

enum class Menu(val menuType: MenuType, val menuName: String, val price: Int) {
    SOUP(MenuType.APPETIZER,"양송이수프", 6000),
    TAPAS(MenuType.APPETIZER,"타파스", 5500),
    SALAD(MenuType.APPETIZER,"시저샐러드", 8000),
    STAKE(MenuType.MAIN,"티본스테이크", 55000),
    BARBEQUE_RIP(MenuType.MAIN,"바비큐립", 54000),
    SEAFOOD_PASTA(MenuType.MAIN,"해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuType.MAIN,"크리스마스파스타", 25000),
    CHOCO_CAKE(MenuType.DESERT,"초코케이크", 15000),
    ICECREAM(MenuType.DESERT,"아이스크림", 5000),
    ZERO_COKE(MenuType.DRINK,"제로콜라", 3000),
    RED_WINE(MenuType.DRINK,"레드와인", 60000),
    SHAMPEIN(MenuType.DRINK,"샴페인", 25000);

    companion object {
        private const val INVALID_MENU = "식당에 없는 메뉴입니다."

        fun convertToMenu(name: String) : Menu {
            return Menu.entries.find { it.menuName == name } ?: throw IllegalArgumentException(
                INVALID_MENU)
        }

        fun validateMenu(name: String): Boolean {
            return Menu.entries.any { it.menuName == name }
        }
    }

}