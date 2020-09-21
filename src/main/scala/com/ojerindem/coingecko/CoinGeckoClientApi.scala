package com.ojerindem.coingecko

import com.ojerindem.coingecko.client.{ApiAddress, CoinGeckoClient}

class CoinGeckoClientApi(implicit coinGeckoApi: ApiAddress) extends CoinGeckoClient {

}

object CoinGeckoClientApi  {
  def apply(apiVersion: Int = 3): CoinGeckoClient = {
    /** The default API passed into the Client. API Version is parsed into address here*/
    implicit def apiAddress = ApiAddress(s"https://api.coingecko.com/api/v$apiVersion")
    new CoinGeckoClientApi()
  }
}
