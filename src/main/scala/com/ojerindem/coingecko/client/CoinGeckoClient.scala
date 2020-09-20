package com.ojerindem.coingecko.client

import com.ojerindem.coingecko.Environment.{Ping}
import com.ojerindem.coingecko.utils.Exceptions.UnknownApiPathException
import com.ojerindem.coingecko.utils.Logging
import io.circe.parser
import scalaj.http.Http
import io.circe.generic.auto._

class CoinGeckoClient(apiVersion: Int) extends Logging  {

  def apiAddress = s"https://api.coingecko.com/api/v$apiVersion"

  def ping: String = {
    val pingApiAddress = s"$apiAddress/ping"
    val ping = Http(pingApiAddress).asString
    val response = ping.body

    val decodingResult = parser.decode[Ping](response)

    decodingResult match {
      case Right(ping) => ping.gecko_says
      case Left(error) => throw new UnknownApiPathException(s"Error when trying to access path: $pingApiAddress")
    }
  }
}

object CoinGeckoClient {
  def apply(apiVersion: Int): CoinGeckoClient = {
    new CoinGeckoClient(apiVersion)
  }
}
