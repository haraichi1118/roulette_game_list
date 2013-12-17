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

  type GameCount = Long

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
        val high = result.filter(r => r.numberList.highLow == 1).length
        val odd = result.filter(r => r.numberList.oddEven == 1).length
        val column = result.groupBy(_.numberList.columnBet).map(x => (x._1, x._2.length))
        val (a, b, c) = (column.get(ColumnBet.A.id).getOrElse(0), column.get(ColumnBet.B.id).getOrElse(0), column.get(ColumnBet.C.id).getOrElse(0))
        val dozen = result.groupBy(_.numberList.dozenBet).map(x => (x._1, x._2.length))
        val (dLow, dMiddle, dHigh) = (dozen.get(DozenBet.LOW.id).getOrElse(0), dozen.get(DozenBet.MIDDLE.id).getOrElse(0), dozen.get(DozenBet.HIGH.id).getOrElse(0))
        val dMiddleAndHigh = dMiddle + dHigh

        implicit val gameCount: GameCount = result.head.roulette.gameConut

        val probability = ProbabilityValue(calculate(black), calculate(high),
          calculate(odd), calculate(a), calculate(b), calculate(c),
          calculate(dLow), calculate(dMiddle), calculate(dHigh), calculate(dMiddleAndHigh))

        RouletteDate(
          result,
          CountValue(black, high, odd, a, b, c, dLow, dMiddle, dHigh, dMiddleAndHigh),
          probability)
    }

  }

  /**
   * 確率の計算をします
   * @param value
   * @param gameCount
   * @return
   */
  private def calculate(value: Long)(implicit gameCount: GameCount): Long = {

    try {
      val answer = BigDecimal.apply(value)./(BigDecimal.apply(gameCount)).
        setScale(2, BigDecimal.RoundingMode.HALF_UP).*(BigDecimal.apply(100))

      answer.toLong
    } catch {
      case e: Exception => 0
    }
  }

  object ColumnBet extends Enumeration {
    val A, B, C = Value
  }

  object DozenBet extends Enumeration {
    val LOW, MIDDLE, HIGH = Value
  }

}
