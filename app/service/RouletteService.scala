package service

import models.{Numbers, RouletteLists, RouletteList, Number}

// 成功回数
case class CountValue(colorCount: Long, highLowCount: Long, OddEvenCount: Long, columnACount: Long, columnBCount: Long,
                      columnCCount: Long, dozenLowCount: Long, dozenMiddleCount: Long, dozenHighCount: Long,
                      dozenMiddleAndHighCount: Long)
// 成功確率
case class ProbabilityValue(colorProbability: Long, highLowProbability: Long, OddEvenProbability: Long,
                            columnAProbability: Long, columnBProbability: Long, columnCProbability: Long,
                            dozenLowProbability: Long, dozenMiddleProbability: Long, dozenHighProbability: Long,
                            dozenMiddleAndHighProbability: Long)

case class RouletteWithNumber(roulette: RouletteList, numberList: Number)

case class RouletteDate(rouletteWithNumber: List[RouletteWithNumber], countValue: CountValue, probabilityValue: ProbabilityValue)

/**
 * Created with IntelliJ IDEA.
 * User: tsuiki
 * Date: 13/12/14
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
object RouletteService {

  def getRouletteDate(playGameId: Long): RouletteDate = {

    // ルーレットデータ取得
    val result: List[RouletteWithNumber] = RouletteLists.findByPlayGameId(playGameId) map {
      rList =>
        // ルーレットデータに対するnumberを取得
        val rouletteNumber: Number = Numbers.findById(rList.rouletteNumber)
        RouletteWithNumber.apply(rList, rouletteNumber)
    }

    result.size match {
      case 0 =>
        val countValue = CountValue.apply(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val probability = ProbabilityValue.apply(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        RouletteDate.apply(List(), countValue, probability)
      case _ =>
        // 集計 TODO もっと良いやり方ありそう
        val black = result.filter(r => r.numberList.color == 1).length
        val high  = result.filter(r => r.numberList.highLow == 1).length
        val odd = result.filter(r => r.numberList.oddEven == 1).length
        val a = result.filter(r => r.numberList.columnBet == 0).length
        val b = result.filter(r => r.numberList.columnBet == 1).length
        val c = result.filter(r => r.numberList.columnBet == 2).length
        val dLow = result.filter(r => r.numberList.dozenBet == 0).length
        val dMiddle = result.filter(r => r.numberList.dozenBet == 1).length
        val dHigh = result.filter(r => r.numberList.dozenBet == 2).length
        val dMiddleAndHigh = dMiddle + dHigh

        val countValue = CountValue.apply(black, high, odd, a, b, c, dLow, dMiddle, dHigh, dMiddle)

        val gameCount = result.head.roulette.gameConut
        val probability = ProbabilityValue.apply(calculate(black, gameCount), calculate(high, gameCount),
          calculate(odd, gameCount), calculate(a, gameCount), calculate(b, gameCount),calculate(c, gameCount),
          calculate(dLow, gameCount), calculate(dMiddle, gameCount), calculate(dHigh, gameCount), calculate(dMiddle, gameCount))

        RouletteDate.apply(result, countValue, probability)
    }

  }

  private def calculate(gameCount: Long, value: Long): Long = {
    try {
      Math.round(value / gameCount) * 100
    } catch {
      case e: Exception => 0
    }
  }

}
