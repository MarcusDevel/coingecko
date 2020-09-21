package com.ojerindem.coingecko.client

import com.ojerindem.coingecko.utils.Exceptions.UnknownApiPathException
import io.circe.parser
import io.circe.generic.auto._

/** Wraps the ping api
 *
 *  One API call used as a check for the API availability */
class CoinGeckoClient(implicit coinGeckoApi: ApiAddress) extends Simple with Logging with CoinGeckoHttp {

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
