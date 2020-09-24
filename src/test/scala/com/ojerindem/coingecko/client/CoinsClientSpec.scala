package com.ojerindem.coingecko.client

import io.circe._, io.circe.parser._
import org.scalatest.flatspec.AnyFlatSpec

class CoinsClientSpec extends AnyFlatSpec {

  behavior of "CoinsClient"

  def isJson(json: String) = {
    val parseJson = parse(json)
    val isJson = parseJson match {
      case Left(failure) => false
      case Right(json) => true
    }
    isJson
  }

  it should "return the list of coins from coingecko as a JSON String" in {
    val result = CoinsClient().coinsList

    val isJsonString = isJson(result)
    assert(isJsonString === true)
  }

  it should "return the list of coins and their markets from coingecko as a JSON String" in {
    val result = CoinsClient().coinsMarkets("gbp","ethereum","decentralized_finance_defi","market_cap_desc","1","1",false,"24h")

    val isJsonString = isJson(result)
    assert(isJsonString === true)
  }

}
