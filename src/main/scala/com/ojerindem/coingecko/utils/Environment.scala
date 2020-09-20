package com.ojerindem.coingecko
object Environment {
  case class Ping(gecko_says: String)
  case class Error(error: String)
}
