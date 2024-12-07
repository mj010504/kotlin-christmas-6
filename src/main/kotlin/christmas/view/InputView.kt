package christmas.view

import camp.nextstep.edu.missionutils.Console
import christmas.domain.VisitDay
import christmas.utils.Validator

object InputView {
    private const val VisitDayScript = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"

    fun getVisitedDay(): VisitDay {

        while (true) {
            try {
                println(VisitDayScript)
                val visitDay = Console.readLine()
                Validator.validateVisitDay(visitDay)
                return VisitDay(visitDay.toInt())
            } catch (e: Exception) {
                println(e.message)
            }
        }


    }
}