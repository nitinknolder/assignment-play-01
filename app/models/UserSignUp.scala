package models

import com.google.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


case class UserDetails (id: Int, name: String, middleName: String, lastName: String,
                        userName: String, password: String, confirmPassword: String,
                        mobileNumber: String, gender: String, age: Int, hobbies: String)


trait UserProfileRepositoryTable extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  val userProfileQuery: TableQuery[UserSignUp] = TableQuery [UserSignUp]

  class UserSignUp (tag: Tag) extends Table[UserDetails](tag, "signUp") {

    def * : ProvenShape[UserDetails] = (id, name, middleName, lastName, userName,
      password, confirmPassword, mobNumber,
      gender, age, hobbies) <> (UserDetails.tupled, UserDetails.unapply)

    def id: Rep[Int] = column [Int]("id", O.PrimaryKey, O.AutoInc)

    def name: Rep[String] = column [String]("FirstName")

    def middleName: Rep[String] = column [String]("MiddleName")

    def lastName: Rep[String] = column [String]("LastName")

    def userName: Rep[String] = column [String]("UserName")

    def password: Rep[String] = column [String]("Password")

    def confirmPassword: Rep[String] = column [String]("ConfirmPassword")

    def mobNumber: Rep[String] = column [String]("MobileNumber")

    def gender: Rep[String] = column [String]("Gender")

    def age: Rep[Int] = column [Int]("Age")

    def hobbies: Rep[String] = column [String]("Hobbies")
  }

}

 class UserProfileInfo @Inject () (protected val dbConfigProvider: DatabaseConfigProvider) extends UserProfileBaseRepository with
  UserProfileBaseRepositoryImplementation with UserProfileRepositoryTable

trait UserProfileBaseRepository {
  def store (data: UserDetails): Future[Boolean]
}

trait UserProfileBaseRepositoryImplementation extends UserProfileBaseRepository {
  self: UserProfileRepositoryTable =>

  import profile.api._

  def store (userData: UserDetails): Future[Boolean] =
    db.run (userProfileQuery += userData) map (_ > 0)
}



