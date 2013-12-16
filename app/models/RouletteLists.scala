package models

import anorm.SqlParser._
import anorm._
import play.api.db.DB
import play.api.Play.current
import anorm.~

case class RouletteList(id: Long, rouletteNumber: Long, playGameId: Long, gameConut: Long)
case class MaxId(id: Long)

/**
 * Created with IntelliJ IDEA.
 * User: tsuiki
 * Date: 13/12/14
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
object RouletteLists {

  val rouletteList = {
    get[Long]("id") ~
      get[Long]("roulette_number") ~
      get[Long]("play_game_id") ~
      get[Long]("game_count") map {
      case id~rouletteNumber~playGameId~gameCount => RouletteList(id, rouletteNumber, playGameId, gameCount)
    }
  }

  val maxId = {
    get[Long]("max_Id") map {
      case maxId => MaxId(maxId)
    }
  }

  def findAll(): List[RouletteList] = DB.withConnection { implicit  c =>
    SQL("select * from roulette_list order by id desc").as(rouletteList *)
  }

  def findByPlayGameId(playGameId: Long): List[RouletteList] = DB.withConnection { implicit  c =>
    SQL("""select * from roulette_list where play_game_id = {play_game_id} order by id desc""")
      .on('play_game_id -> playGameId)
      .as(rouletteList *)
  }

  def findMaxId(playGameId: Long): Option[MaxId] = DB.withConnection { implicit  c =>
    SQL("""select max(id) as max_Id from roulette_list where play_game_id = {play_game_id}""")
      .on('play_game_id -> playGameId)
      .as(maxId.singleOpt)
  }

  def create(rouletteNumber: Long, playGameId: Long, gameCount: Long) {
    DB.withConnection { implicit c =>
      SQL("""insert into roulette_list (roulette_number, play_game_id, game_count) values ({roulette_number}, {play_game_id}, {game_count})""")
        .on (
          'roulette_number -> rouletteNumber,
          'play_game_id -> playGameId,
          'game_count -> gameCount).executeUpdate()
      }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from roulette_list where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

}
