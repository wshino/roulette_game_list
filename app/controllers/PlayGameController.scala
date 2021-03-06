package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.PlayGames

/**
 * Created with IntelliJ IDEA.
 * User: tsuiki
 * Date: 13/12/14
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */
object PlayGameController extends Controller {

  val playGameform = Form(
    "title" -> nonEmptyText
  )

  def index() = Action {
    Ok(views.html.playList(PlayGames.findAll(), playGameform))
  }

  def create = Action { implicit request =>
    playGameform.bindFromRequest.fold(
      errors => BadRequest(views.html.playList(PlayGames.findAll(), errors)),
      title => {
        PlayGames.create(title)
        Redirect(routes.PlayGameController.index)
      }
    )
  }

  def delete(id: Long) = Action {
    PlayGames.delete(id)
    Redirect(routes.PlayGameController.index)
  }


}
