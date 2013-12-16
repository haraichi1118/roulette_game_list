package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.RouletteLists
import service.RouletteService

/**
 * Created with IntelliJ IDEA.
 * User: tsuiki
 * Date: 13/12/14
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
object RouletteListController extends Controller {

  val rouletteListform = Form(
    "rouletteNumber" -> number
  )

  /**
   * ゲームIDを元にルーレット一覧を表示します
   * @param playGameId
   * @return
   */
  def index(playGameId: Long) = Action {
    println("RouletteListController#index id=" + playGameId)
    Ok(views.html.rouletteList(RouletteService.getRouletteDate(playGameId), rouletteListform, playGameId))
  }

  /**
   * 登録処理
   * @param playGameId
   * @return
   */
  def create(playGameId: Long) = Action { implicit request =>
    println("RouletteListController#create id=" + playGameId)
    rouletteListform.bindFromRequest.fold(
      errors => BadRequest(views.html.rouletteList(RouletteService.getRouletteDate(playGameId), errors, playGameId)),
      rouletteNumber => {
        val id = RouletteLists.findByPlayGameId(playGameId)

        println("RouletteListController#create findByPlayGameId =" + id)

        val maxCount: Long = id.length match {
          case 0 => 0
          case _ => id.head.gameConut
        }
        println("RouletteListController#create maxCount =" + maxCount)

        RouletteLists.create(rouletteNumber, playGameId, (maxCount+1))
        Redirect(routes.RouletteListController.index(playGameId))
      }
    )
  }

  /**
   * 削除処理
   * @param playGameId
   * @param id
   * @return
   */
  def delete(playGameId: Long, id: Long) = Action {
    println("RouletteListController#delete id=" + playGameId)
    RouletteLists.delete(id)
    Redirect(routes.RouletteListController.index(playGameId))
  }

}
