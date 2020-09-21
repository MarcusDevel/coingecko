package com.ojerindem.coingecko.app

import com.ojerindem.coingecko.CoinGeckoClientApi

/** Prints the result of the ping API*/

object Application extends App {
  val coinGeckoInstance = CoinGeckoClientApi()
  print(coinGeckoInstance.ping)
}

//~/.ivy2/local/com.ojerindem.coingecko/coingecko_2.13/0.1-SNAPSHOT/
//~/.ivy2/local/com.ojerindem.coingecko/_2.13/coingecko/ivys/ivy.xml