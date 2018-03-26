package users

import play.api.data.Form
import play.api.data.Forms.{mapping,text}

case class LoginDetails(userName: String, Password:String)

class LoginForm {

  val SignInForm= Form(mapping(
      "userName" -> text.verifying ("please Enter Your Username",_.nonEmpty),
      "password" -> text.verifying("please Enter Your Password", _.nonEmpty)
    )(LoginDetails.apply)(LoginDetails.unapply))

}


