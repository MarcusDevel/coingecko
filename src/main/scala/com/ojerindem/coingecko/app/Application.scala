package com.ojerindem.coingecko.app

import com.ojerindem.coingecko.client.{CoinGeckoClient}
import com.ojerindem.coingecko.utils.Logging

/** Prints the result of the ping API*/

object Application extends App with Logging {
  val coinGeckoInstance = CoinGeckoClient()
  println(coinGeckoInstance.ping)
}
