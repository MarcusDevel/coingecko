package com.ojerindem.coingecko.client
import org.scalatest.flatspec.AnyFlatSpec

class CoinGeckoHttpSpec extends AnyFlatSpec with CoinGeckoHttp  {
  behavior of "CoinGeckHttp"

  it should "return the body of an API call" in {
    assert(getBody("https://api.coingecko.com/api/v3/ping") === "{\"gecko_says\":\"(V3) To the Moon!\"}")
  }


}
