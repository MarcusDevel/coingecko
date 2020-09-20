package com.ojerindem.coingecko.client
import com.ojerindem.coingecko.ApiAddress
import com.ojerindem.coingecko.utils.Exceptions.UnknownApiPathException
import org.scalatest.flatspec.AnyFlatSpec

class CoinGeckoClientSpec extends AnyFlatSpec {
  behavior of "CoinGeckoClient"

  it should "return the default 'ping' message" in {
    assert(CoinGeckoClient(3).ping === "(V3) To the Moon!")
  }

  it should "throw an UnknownApiPathException for an incorrect API address" in {
    implicit val apiAddress = ApiAddress(s"https://api.coingecko.com/api/v3/pong")
    class CoinGeckoClientTest extends CoinGeckoClient() {}

    assertThrows[UnknownApiPathException] {
      (new CoinGeckoClientTest).ping
    }
  }
}
