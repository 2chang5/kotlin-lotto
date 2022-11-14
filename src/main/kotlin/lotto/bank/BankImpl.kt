package lotto.bank

import lotto.Lotto
import lotto.bank.Grade.Companion.SECOND_AND_THIRD_WINNING_MAIN_LOTTO_NUMBERS_COUNT
import kotlin.properties.Delegates

class BankImpl : Bank {
    override val prizeGrades = mutableListOf<Int>(0, 0, 0, 0, 0)
    override var earningRate by Delegates.notNull<Float>()
    override fun calcPrizeGrade(receivedMainLottoNumbers: List<Int>, receivedBonusLottoNumber: Int, lotto: Lotto) {
        val winningMainLottoNumbersCount = calcWinningMainLottoNumbers(receivedMainLottoNumbers, lotto)
        val winningBonusLottoNumberState = calcWinningBonusLottoNumber(receivedBonusLottoNumber, lotto)

        when (winningMainLottoNumbersCount) {
            Grade.FIRST.winningMainLottoNumbersCount -> prizeGrades[Grade.FIRST.prizeGradesIndex] += 1
            SECOND_AND_THIRD_WINNING_MAIN_LOTTO_NUMBERS_COUNT -> {
                if (winningBonusLottoNumberState) {
                    prizeGrades[Grade.SECOND.prizeGradesIndex] += 1
                    return
                }
                prizeGrades[Grade.THIRD.prizeGradesIndex] += 1
            }
            Grade.FOURTH.winningMainLottoNumbersCount -> prizeGrades[Grade.FOURTH.prizeGradesIndex] += 1
            Grade.FIFTH.winningMainLottoNumbersCount -> prizeGrades[Grade.FIFTH.prizeGradesIndex] += 1
        }
    }

    override fun calcWinningMainLottoNumbers(receivedMainLottoNumbers: List<Int>, lotto: Lotto): Int =
        receivedMainLottoNumbers.intersect(lotto.getLottoNumbers().toSet()).size

    override fun calcWinningBonusLottoNumber(receivedBonusLottoNumber: Int, lotto: Lotto): Boolean =
        receivedBonusLottoNumber in lotto.getLottoNumbers()

    override fun calcEarningRate(purchaseAmount: Int, prizeGrades: List<Int>) {
        TODO("Not yet implemented")
    }
}
