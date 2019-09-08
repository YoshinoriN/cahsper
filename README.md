# Cahsper

|Build|Quality|Coverage|
|---|---|---|
|[![](https://travis-ci.org/YoshinoriN/cahsper.svg?branch=master)](https://travis-ci.org/YoshinoriN/cahsper)|[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6981167737cf4e21b6a9cf74d5c36c0a)](https://www.codacy.com/app/YoshinoriN/cahsper?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=YoshinoriN/cahsper&amp;utm_campaign=Badge_Grade)|[![Coverage Status](https://coveralls.io/repos/github/YoshinoriN/cahsper/badge.svg?branch=master)](https://coveralls.io/github/YoshinoriN/cahsper?branch=master)|

# Documentation

* [API](https://yoshinorin.github.io/cahsper/)
* [Development](./docs/dev)

# Requirements

* sbt 1.2.x
* Scala 2.12.9
* JVM 11
* MariaDB 10.x

# Set up

TODO

# Configuration

Cahsper backend (API server) read all settings from the system environment variable. You have to set the following system environment variables.

## Database & HTTP Server

|Property|Description|Type|Default|Example|
|---|---|---|---|---|
|`CAHSPER_DB_DATASOURCE_URL`|Data source url for JDBC connection.|`string`|-|`jdbc:mariadb://127.0.0.1/cahsper?useUnicode=true&characterEncoding=utf8mb4`|
|`CAHSPER_DB_USER`|Database user name.|`string`|-|`root`|
|`CAHSPER_DB_PASSWORD`|Database user password.|`string`|-|`pass`|
|`CAHSPER_DB_CONNECTION_TIMEOUT`|Database connection timeout.|`int`|`30000`|`30000`|
|`CAHSPER_DB_MAXIMUM_POOLSIZE`|Connection pool size.|`int`|`6`|`6`|
|`CAHSPER_HTTP_HOST`|Http server host.|`string`|`0.0.0.0`|`127.0.0.1`|
|`CAHSPER_HTTP_PORT`|Http server port.|`int`|`9001`|`9001`|

## Authentication

Cahsper backend (API server) use AWS Cognito for authentication. Please create AWS Cognito user pool and set the following system environment variable.

|Property|Description|Type|Default|Example|
|---|---|---|---|---|
|`CAHSPER_AWS_COGNITO_JWK `|AWS Cognito jwk URL|`string`|-|`https://cognito-idp.{region}.amazonaws.com/{userPoolId}/.well-known/jwks.json`|

# API Execution example

<details>
  <summary>Get all users comments</summary>

```sh
$ curl -D - -X GET 127.0.0.1:9001/comments

HTTP/1.1 200 OK
Server: akka-http/10.1.9
Date: Sat, 07 Sep 2019 13:28:57 GMT
Content-Type: application/json
Content-Length: 307

[
  {
    "id" : 1,
    "user" : "YoshinoriN",
    "comment" : "test",
    "createdAt" : 1567862313
  },
  {
    "id" : 2,
    "user" : "TODO",
    "comment" : "Hello Cahsper!!",
    "createdAt" : 1567862760
  }
]
```

</details>

<details>
  <summary>Create new comment</summary>

```sh
$ curl -D - -X POST -H "Content-Type: application/json" -d '{"comment":"Hello Cahsper!!"}' 127.0.0.1:9001/comments

HTTP/1.1 201 Created
Server: akka-http/10.1.9
Date: Sat, 07 Sep 2019 13:26:01 GMT
Content-Type: application/json
Content-Length: 94
{
  "id" : 1,
  "user" : "TODO",
  "comment" : "Hello Cahsper!!",
  "createdAt" : 1567862760
}
```

</details>

# Using Stacks

|Stack|-|
|---|---|
|[Scala](https://www.scala-lang.org/)|-|
|[Akka HTTP](https://akka.io/docs/)|HTTP server|
|[akka-http-cors](https://github.com/lomigmegard/akka-http-cors)|CORS for Akka HTTP|
|[Flyway](https://flywaydb.org/)|Database Migration|
|[quill](https://getquill.io/)|Database Library|
|[circe](https://circe.github.io/circe/)|JSON Library|
|[MariaDB](https://mariadb.org/)|Database|
|[ScalaTest](http://www.scalatest.org/)|Unit test|
|[Scalafmt](https://scalameta.org/scalafmt/)|Code formatter|
|[TravisCI](https://travis-ci.org/YoshinoriN/cahsper)|CI|
|[Codacy](https://app.codacy.com/manual/YoshinoriN/cahsper/dashboard)|Check Code quality|
|[COVERALLS](https://coveralls.io/github/YoshinoriN/cahsper?branch=master)|Coverage report|
|[ReDoc](https://github.com/Rebilly/ReDoc)|Generate API documentation |
|[GitHub Pages](https://pages.github.com/)|Hosting API docuementation|
|AWS Cognito|Authentication|
|[Nimbus JOSE](https://connect2id.com/products/nimbus-jose-jwt)|JWT validator|
