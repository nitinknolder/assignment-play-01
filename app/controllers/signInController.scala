//package controllers
//
//import Service.LoginService
//import com.google.inject.Inject
//import models.UserProfileInfo
//import play.api.db.slick.DatabaseConfigProvider
//import play.api.i18n
//import play.api.i18n.I18nSupport
//import play.api.mvc.{AbstractController, AnyContent, ControllerComponents}
//import play.api.mvc._
//import users.{LoginDetails, LoginForm}
//
//import scala.concurrent.Future
//
//class signInController @Inject () (protected val dbConfigProvider: DatabaseConfigProvider, login: LoginForm, userProfileInfo: UserProfileInfo, cc: ControllerComponents, service: LoginService) extends AbstractController (cc) with I18nSupport {
//
//  implicit val messages: i18n.MessagesApi = cc.messagesApi
//
//
//  def validateUser (): Action[AnyContent] = Action.async { implicit request =>
//    login.SignInForm.bindFromRequest ().fold (
//      formWithError => {
//        Future.successful (BadRequest (views.html.index ()))
//      },
//      userData => {
//        val user = LoginDetails (userData.userName, userData.Password)
//        userProfileInfo.store (user).map {
//          case true
//          =>
//            Redirect (routes.HomeController.userProfile ()).withSession ("username" -> userData.userName)
//              .flashing ("Success" -> "User Logged In SuccessFully")
//          case false
//          =>
//            Redirect (routes.HomeController.index ()).flashing ("error" -> "You Have Entered Wrong Credentials!!!")
//        }
//      }
//    )
//  }
//
//
//}
