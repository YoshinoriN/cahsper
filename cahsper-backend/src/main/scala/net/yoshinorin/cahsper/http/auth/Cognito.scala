package net.yoshinorin.cahsper.http.auth

import akka.http.scaladsl.model.headers.{HttpChallenge, OAuth2BearerToken}
import akka.http.scaladsl.server.Directives.authenticateOrRejectWithChallenge
import akka.http.scaladsl.server.directives.{AuthenticationDirective, AuthenticationResult}
import net.yoshinorin.cahsper.auth.aws.Cognito
import net.yoshinorin.cahsper.domains.models.jwt.JwtToken
import net.yoshinorin.cahsper.domains.models.jwt.aws.cognito.Jwt.convertJwtClaims
import net.yoshinorin.cahsper.domains.models.users.UserName

import scala.concurrent.Future

class Cognito extends Auth {

  // TODO: Clean up
  override def authenticate: AuthenticationDirective[UserName] = {

    authenticateOrRejectWithChallenge[OAuth2BearerToken, UserName] {
      case Some(OAuth2BearerToken(bearerToken)) => {
        Cognito.validateJwt(JwtToken(bearerToken)) match {
          case Right(jwtClaimsSet) => {
            jwtClaimsSet.toString.toJwtClaims match {
              case Right(jwtClaims) => Future.successful(AuthenticationResult.success(UserName(jwtClaims.username)))
              case Left(_) =>
                Future.successful(AuthenticationResult.failWithChallenge(HttpChallenge("Bearer", None, Map("error" -> "Internal server error"))))
            }
          }
          case Left(_) =>
            Future.successful(AuthenticationResult.failWithChallenge(HttpChallenge("Bearer", None, Map("error" -> "Invalid token"))))
        }
      }
      case _ =>
        Future.successful(AuthenticationResult.failWithChallenge(HttpChallenge("Bearer", None, Map("error" -> "Requires authentication"))))
    }

  }

}
