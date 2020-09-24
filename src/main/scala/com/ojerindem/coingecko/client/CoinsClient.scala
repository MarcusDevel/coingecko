package com.ojerindem.coingecko.client

import com.ojerindem.coingecko.utils.Logging

class CoinsClient(implicit apiAddress: ApiAddress) extends CoinGeckoHttp with Logging {

  private val coinsAddr = apiAddress.address + "/coins"

  def coinsList = {
    val coinsListAddr = coinsAddr + "/list"
    val response = getBody(coinsListAddr)
    response
  }



}

object CoinsClient  {
  def apply(apiVersion: Int = 3): CoinsClient = {
    /** The default API passed into the Client. API Version is parsed into address here*/
    implicit def apiAddress = ApiAddress(s"https://api.coingecko.com/api/v$apiVersion")
    new CoinsClient()
  }
}
