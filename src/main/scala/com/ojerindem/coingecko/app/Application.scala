package com.ojerindem.coingecko.app

import com.ojerindem.coingecko.CoinGeckoClientApi

/** Prints the result of the ping API*/

object Application extends App {
  val coinGeckoInstance = CoinGeckoClientApi()
  print(coinGeckoInstance.simpleTokenPriceJson("0x0bc529c00c6401aef6d220be8c6ea1667f6ad93e","gbp"))
  print(coinGeckoInstance.simpleSupportedVsCurrenciesJson)
}