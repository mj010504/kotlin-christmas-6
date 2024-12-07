package christmas.domain


import christmas.constants.Menu
import christmas.constants.ORDER_FORMAT
import christmas.utils.Validator
import christmas.utils.Validator.getErrorMessage

class Order(private val menu: Menu, private val quantity: Int) {

    init {
        validateQuantity()
    }

    private fun validateQuantity() {
        require(quantity >= MIN_QUANTITY) {
            getErrorMessage(INVALID_ORDER)
        }
    }

    companion object {
        private const val MIN_QUANTITY = 1
        private const val INVALID_ORDER = "유효하지 않은 주문입니다."
    }
}