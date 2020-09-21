package com.ojerindem.coingecko.app

import com.ojerindem.coingecko.client.Logging
import com.ojerindem.coingecko.CoinGeckoClientApi

/** Prints the result of the ping API*/

object Application {
  val coinGeckoInstance = CoinGeckoClientApi()
}
