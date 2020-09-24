package com.ojerindem.coingecko.client

import com.ojerindem.coingecko.utils.Exceptions.UnknownApiPathException
import com.ojerindem.coingecko.utils.Logging
import io.circe.parser
import io.circe.generic.auto._

/** Wraps the ping api
 *
 *  One API call used as a check for the API availability */
class CoinGeckoClient(implicit coinGeckoApi: ApiAddress) extends CoinGeckoHttp with Logging {

  /** The default api address for the CoinGecko API */
  private def apiAddress = coinGeckoApi.address

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

object CoinGeckoClient  {
  def apply(apiVersion: Int = 3): CoinGeckoClient = {
    /** The default API passed into the Client. API Version is parsed into address here*/
    implicit def apiAddress = ApiAddress(s"https://api.coingecko.com/api/v$apiVersion")
    new CoinGeckoClient()
  }
}
