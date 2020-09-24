import scalaj.http.Http
import com.ojerindem.coingecko.utils.Logging

package com.ojerindem.coingecko.client {

  trait CoinGeckoHttp extends Logging {

  /**
   * Returns a body of an API call
   * @param apiAddress URL to query
   * @return Body of API call
   */
    private [client] def getBody(apiAddress: String) = {
      val ping = Http(apiAddress).asString
      val body = ping.body
      body
    }
  }
}
