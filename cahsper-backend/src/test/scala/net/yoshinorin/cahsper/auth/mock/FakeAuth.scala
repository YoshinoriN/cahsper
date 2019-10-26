package net.yoshinorin.cahsper.auth

import akka.http.scaladsl.model.headers.{HttpChallenge, OAuth2BearerToken}
import akka.http.scaladsl.server.Directives.authenticateOrRejectWithChallenge
import akka.http.scaladsl.server.directives.{AuthenticationDirective, AuthenticationResult}
import net.yoshinorin.cahsper.definitions.User
import net.yoshinorin.cahsper.http.auth.Auth

import scala.concurrent.Future

class FakeAuth(userName: String) extends Auth {

  override def authenticate: AuthenticationDirective[User] = {
    authenticateOrRejectWithChallenge[OAuth2BearerToken, User] {
      case Some(OAuth2BearerToken(bearerToken)) => {
        Future.successful(AuthenticationResult.success(User(userName)))
      }
      case _ =>
        Future.successful(AuthenticationResult.failWithChallenge(HttpChallenge("Bearer", None, Map("error" -> "Requires authentication"))))
    }
  }

}