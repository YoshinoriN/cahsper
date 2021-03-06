package net.yoshinorin.cahsper.http.routes

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.circe.syntax._
import net.yoshinorin.cahsper.domains.services.CommentService
import net.yoshinorin.cahsper.models.request.QueryParamater

class CommentRoute(
  commentService: CommentService
) {

  def route: Route = {
    pathPrefix("comments") {
      pathEndOrSingleSlash {
        get {
          parameters("page".as[Int].?, "limit".as[Int].?, "from".as[Long].?, "to".as[Long].?, "order".as[String].?) { (page, limit, from, to, order) =>
            onSuccess(commentService.getAll(QueryParamater(page, limit, from, to, order))) { result =>
              complete(HttpResponse(OK, entity = HttpEntity(ContentTypes.`application/json`, s"${result.asJson}")))
            }
          }
        }
      }
    }
  }

}
