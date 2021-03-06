package net.yoshinorin.cahsper.config

import com.typesafe.config.{Config, ConfigFactory}

object Config {

  private[this] val config: Config = ConfigFactory.load

  val dbUrl: String = config.getString("db.ctx.dataSource.url")
  val dbUser: String = config.getString("db.ctx.dataSource.user")
  val dbPassword: String = config.getString("db.ctx.dataSource.password")

  val httpHost: String = config.getString("http.host")
  val httpPort: Int = config.getInt("http.port")

  val awsCognitoJwkIss: String = config.getString("aws.cognito.iss").split("/").last
  val awsCognitoJwkUrl: String = config.getString("aws.cognito.iss") + ("/.well-known/jwks.json")
  val awsCognitoAppClientId: String = config.getString("aws.cognito.appClientId")

}
