package controllers

import javax.inject._

import models.{UserDetails, UserProfileInfo}
import play.api.db.slick.DatabaseConfigProvider
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._
import users.UsersForm
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */

@Singleton
class HomeController @Inject () (protected val dbConfigProvider: DatabaseConfigProvider, user: UsersForm, userProfileInfo: UserProfileInfo, cc: ControllerComponents) extends AbstractController (cc) with I18nSupport {
  implicit val messages: MessagesApi = cc.messagesApi

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index () = Action { implicit request: Request[AnyContent] =>
    Ok (views.html.index ())
  }

  def userProfile = Action { implicit request: Request[Any] => {
    Ok (views.html.profilepage ())
  }
  }

  def signUp = Action { implicit request: Request[AnyContent] =>
    Ok (views.html.signup (user.userForm))
  }


  def addUser (): Action[AnyContent] = Action.async { implicit request =>

    user.userForm.bindFromRequest ().fold (
      formWithError => {
        Future.successful (BadRequest (views.html.signup (formWithError)))
      },
      dataUser => {

        val user = UserDetails (1, dataUser.name, dataUser.middleName, dataUser.lastName,
          dataUser.userName, dataUser.password, dataUser.confirmPassword,
          dataUser.mobileNumber, dataUser.gender, dataUser.age, dataUser.hobbies)
        userProfileInfo.store (user).map {
          case true =>
            Redirect (routes.HomeController.userProfile ()).withSession ("userName" -> dataUser.userName)
              .flashing ("Success" -> "user Successfully Created")
          case false =>
            Ok ("Error Occurred!!!")
        }
      }
    )
  }
}