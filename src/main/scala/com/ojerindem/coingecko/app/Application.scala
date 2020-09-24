package com.ojerindem.coingecko.app

import com.ojerindem.coingecko.client.{CoinGeckoClient, CoinsClient, SimpleClient}
import com.ojerindem.coingecko.utils.Logging

object Application extends App with Logging {
  // Can be done a better way for sure
  configureLogging

  logger.info(s"TESTING INTERFACES...")
  logger.info(s"Running CoinGeckoClient api calls:")
  val coinGeckoInstance = CoinGeckoClient()
  println(coinGeckoInstance.ping)

  logger.info(s"Running SimpleClient api calls:")
  val simpleClientInstance = SimpleClient()
  println(simpleClientInstance.simplePriceJson("ethereum","gbp"))
  println(simpleClientInstance.simpleTokenPriceJson("0x0bc529c00c6401aef6d220be8c6ea1667f6ad93e","gbp"))
  println(simpleClientInstance.simpleSupportedVsCurrenciesJson)

  logger.info(s"Running CoinClient api calls:")
  val coinClientInstance = CoinsClient()
  println(coinClientInstance.coinsList)
}