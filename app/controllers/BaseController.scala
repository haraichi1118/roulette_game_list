package controllers

import play.api.mvc.{Controller, AnyContent, Request}
import play.api.libs.json.JsValue

/**
 * Created with IntelliJ IDEA.
 * User: B03932
 * Date: 13/12/18
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
trait BaseController extends Controller {

  def response(req: Request[AnyContent], jsObject: JsValue) = {
    req.queryString.get("callback").flatMap(_.headOption) match {
      case Some(x) => Ok(x + "(" + jsObject + ")").as("application/javascript; charset=utf-8")
      case None => Ok(jsObject).as("application/json; charset=utf-8")
    }
  }

}
