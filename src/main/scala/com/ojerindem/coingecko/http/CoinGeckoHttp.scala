package com.ojerindem.coingecko.http

import scalaj.http.Http

trait CoinGeckoHttp {

  /**
   * Returns a body of an API call
   * @param apiAddress URL to query
   * @return Body of API call
   */
  def getBody(apiAddress: String) = {
    val ping = Http(apiAddress).asString
    val body = ping.body
    body
  }

}
