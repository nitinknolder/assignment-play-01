package users

import play.api.data.Form
import play.api.data.Forms._

case class UserInformation (name: String, middleName: String, lastName: String, userName: String,
                            password: String, confirmPassword: String, mobileNumber: String,
                            gender: String, age: Int, hobbies: String)

class UsersForm {
  val userForm = Form (mapping (
    "Name" -> text.verifying ("Please fill the required Field", _.nonEmpty),
    "MiddleName" -> text,
    "LastName" -> text.verifying ("Please fill the required Field", _.nonEmpty),
    "UserName" -> text.verifying ("Please fill the required Field", _.nonEmpty),
    "Password" -> text.verifying("Please Enter Your Password",_.nonEmpty),
    "ConfirmPassword" -> text.verifying("Please ReEnter Your Password",_.nonEmpty),
    "MobileNumber" -> text,
    "Gender" -> text,
    "Age" -> number(min = 18, max = 75),
    "Hobbies" -> text
  )(UserInformation.apply)(UserInformation.unapply))
}






