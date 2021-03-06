package com.ojerindem.coingecko.client
import com.ojerindem.coingecko.utils.Exceptions.VsCurrencyNotFoundException
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest._
import matchers.should._

class SimpleClientSpec extends AnyFlatSpec with Matchers {

  behavior of "SimpleClient"

  it should "return a json string for a valid coin pair" in {
    val resultJson =
      SimpleClient()
        .simplePriceJson("ethereum","gbp")
    resultJson should fullyMatch regex
    """\{"ethereum":\{"gbp":\d+.\d+,"gbp_market_cap":\d+.\d+,"gbp_24h_vol":\d+.\d+,"gbp_24h_change":\-?\d+.\d+,"last_updated_at":\d+\}\}"""
  }

  it should "return a json string for a valid token" in {
    val resultJson =
      SimpleClient()
        .simpleTokenPriceJson("0x0bc529c00c6401aef6d220be8c6ea1667f6ad93e","gbp")
    resultJson should fullyMatch regex
      """\{".+":\{"gbp":\d+.\d+,"gbp_market_cap":\d+.\d+,"gbp_24h_vol":\d+.\d+,"gbp_24h_change":\-?\d+.\d+,"last_updated_at":\d+\}\}"""
  }

  it should "throw an VsCurrencyNotFoundException for an unknown/incorrect currency" in {
    val vsCurrency = "gbb"

    assertThrows[VsCurrencyNotFoundException] {
      SimpleClient().runIfValidVsCurrency(vsCurrency){
        "someString"
      }
    }
  }

  it should "return a json string of all the valid vsCurrencies that can be used" in {
    val resultJson =
      SimpleClient()
        .simpleSupportedVsCurrenciesJson.replaceAll("\\[|\\]","")
    resultJson should fullyMatch regex
      """[\"\w+\"\,*]+""""
  }

}
