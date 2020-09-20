package com.ojerindem.coingecko.client

import com.ojerindem.coingecko.{ApiAddress, Ping}
import com.ojerindem.coingecko.utils.Exceptions.UnknownApiPathException
import com.ojerindem.coingecko.utils.Logging
import io.circe.parser
import com.ojerindem.coingecko.http.CoinGeckoHttp
import io.circe.generic.auto._

/** Wraps the ping api
 *
 *  One API call used as a check for the API availability */
class CoinGeckoClient(implicit coinGeckoApi: ApiAddress) extends Simple with Logging with CoinGeckoHttp {

  /** The default api address for the CoinGecko API */
  def apiAddress = coinGeckoApi.address

  /** Returns the formatted body of the ping API call */
  def ping: String = {
    val pingApiAddress = s"$apiAddress/ping"
    val response = getBody(pingApiAddress)

    val decodingResult = parser.decode[Ping](response)

    decodingResult match {
      case Right(ping) => ping.gecko_says
      case Left(_) => throw new UnknownApiPathException(s"Error when trying to access path: $pingApiAddress")
    }
  }
}

/** Companion Object for the CoinGeckoClient */
object CoinGeckoClient {
  def apply(apiVersion: Int = 3): CoinGeckoClient = {
    /** The default API passed into the Client. API Version is parsed into address here*/
    implicit def apiAddress = ApiAddress(s"https://api.coingecko.com/api/v$apiVersion")
    new CoinGeckoClient()
  }
}
