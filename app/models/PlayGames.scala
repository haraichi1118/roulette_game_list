package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class PlayGame(id: Long, title: String)

/**
 * Created with IntelliJ IDEA.
 * User: tsuiki
 * Date: 13/12/14
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
object PlayGames {

  val playGame = {
    get[Long]("id") ~
      get[String]("title") map {
      case id~title => PlayGame(id, title)
    }
  }

  def findAll(): List[PlayGame] = DB.withConnection { implicit  c =>
    SQL("select * from play_game").as(playGame *)
  }

  def create(title: String) {
    DB.withConnection { implicit c =>
      SQL("insert into play_game (title) values ({title})").on(
        'title -> title
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from play_game where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

}
