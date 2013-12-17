package models

import anorm.SqlParser._
import anorm._
import play.api.db.DB
import anorm.~
import play.api.Play.current

/**
 * ルーレット
 * @param id
 * @param rouletteNumber ルーレット番号
 * @param color 0：赤、1：黒
 * @param highLow 0：LOW、1：HIGH
 * @param oddEven 0：EVEN、1：ODD
 * @param columnBet 0：LOW、1：MIDDLE、2：HIGH
 * @param dozenBet 0：1-12、1：13-24、2：25-36
 */
case class Number(id: Long, rouletteNumber: Long, color: Long, highLow: Long, oddEven: Long, columnBet: Long, dozenBet: Long)

/**
 * Created with IntelliJ IDEA.
 * User: tsuiki
 * Date: 13/12/14
 * Time: 20:40
 * To change this template use File | Settings | File Templates.
 */
object Numbers {

  val number = {
    get[Long]("id") ~
      get[Long]("roulette_number") ~
      get[Long]("color") ~
      get[Long]("high_low") ~
      get[Long]("odd_even") ~
      get[Long]("column_bet") ~
      get[Long]("dozen_bet") map {
      case id~rouletteNumber~color~highLow~oddEven~columnBet~dozenBet=> Number(id, rouletteNumber, color, highLow, oddEven, columnBet, dozenBet)
    }
  }

  def findAll(): List[Number] = DB.withConnection { implicit  c =>
    SQL("select * from number").as(number *)
  }

  def findById(rouletteNumber: Long): Number = DB.withConnection { implicit  c =>
    val q =
      """
        |SELECT
        |  *
        |FROM
        |  number
        |WHERE
        |  roulette_number = {roulette_number}
      """.stripMargin

    SQL(q).on('roulette_number -> rouletteNumber).as(number.single)
  }


}
