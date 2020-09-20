package com.ojerindem.coingecko.app

import com.ojerindem.coingecko.client.CoinGeckoClient
import com.ojerindem.coingecko.utils.Logging

object Application extends App with Logging {
  println(CoinGeckoClient(apiVersion = 3).ping)
}
