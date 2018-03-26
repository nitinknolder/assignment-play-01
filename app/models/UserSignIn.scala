
//package models
//
//import com.google.inject.Inject
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.JdbcProfile
//import slick.lifted.ProvenShape
//import users.LoginDetails
//
//import scala.concurrent.Future
//
//trait UserProfileRepositoryTable extends HasDatabaseConfigProvider[JdbcProfile] {
//
//  import profile.api._
//
//  val userProfileQuery: TableQuery[UserSignIn] = TableQuery [UserSignIn]
//
//  class UserSignIn (tag: Tag) extends Table[LoginDetails](tag, "signUp") {
//
//    def * : ProvenShape[LoginDetails] = (userName,
//      password) <> (LoginDetails.tupled, LoginDetails.unapply)
//
//    def userName: Rep[String] = column [String]("UserName")
//
//    def password: Rep[String] = column [String]("Password")
//
//  }
//
//}
//
//class UserProfileInfo @Inject () (protected val dbConfigProvider: DatabaseConfigProvider) extends UserProfileBaseRepository with
//  UserProfileBaseRepositoryImplementation with UserProfileRepositoryTable
//
//trait UserProfileBaseRepository {
//  def store (data: UserDetails): Future[Boolean]
//}
//
//trait UserProfileBaseRepositoryImplementation extends UserProfileBaseRepository {
//  self: UserProfileRepositoryTable =>
//
//  import profile.api._
//
//  def store (userData: UserDetails): Future[Boolean] =
//    db.run (userProfileQuery += userData) map (_ > 0)
//}



