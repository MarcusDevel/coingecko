package com.ojerindem.coingecko.app

import com.ojerindem.coingecko.CoinGeckoClientApi

/** Prints the result of the ping API*/

object Application extends App {
  val coinGeckoInstance = CoinGeckoClientApi()
  println(coinGeckoInstance.simplePrice("yearn-finance","gbp"))
  println(coinGeckoInstance.simplePrice("yearn-finance","gbp",false,false,false,false))

  println(coinGeckoInstance.simplePriceJson("yearn-finance","gbp"))
  println(coinGeckoInstance.simplePriceJson("yearn-finance","gbp",false,false,false,false))

  println(coinGeckoInstance.simpleTokenPrice("0x0bc529c00c6401aef6d220be8c6ea1667f6ad93e","gbp"))
  println(coinGeckoInstance.simpleTokenPrice("0x0bc529c00c6401aef6d220be8c6ea1667f6ad93e","gbp","ethereum",false,false,false,false))

  println(coinGeckoInstance.simpleTokenPriceJson("0x0bc529c00c6401aef6d220be8c6ea1667f6ad93e","gbp"))
  println(coinGeckoInstance.simpleTokenPriceJson("0x0bc529c00c6401aef6d220be8c6ea1667f6ad93e","gbp","ethereum",false,false,false,false))
}