package com.ojerindem.coingecko.client
import com.ojerindem.coingecko.ApiAddress
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest._
import matchers.should._

class SimpleSpec extends AnyFlatSpec with Matchers {
  implicit val apiAddress = ApiAddress("https://api.coingecko.com/api/v3")
  val simpleInstance = new Simple()

  behavior of "Simple"

  it should "return a json string for a valid coin pair" in {
    val resultJson =
      simpleInstance
        .simplePriceJson("ethereum","gbp","true","true","true","true")
    resultJson should fullyMatch regex
    """\{"ethereum":\{"gbp":\d+.\d+,"gbp_market_cap":\d+.\d+,"gbp_24h_vol":\d+.\d+,"gbp_24h_change":\-?\d+.\d+,"last_updated_at":\d+\}\}"""
  }

  it should "return a formatted string for a valid coin pair as a case class" in {
    val resultSimplePrice =
      simpleInstance
        .simplePrice("ethereum","gbp","true","true","true","true")
    assert(
      resultSimplePrice.price_in_currency != 0.0 &
      resultSimplePrice.market_cap != 0.0 &
      resultSimplePrice.twenty_four_hr_volume != 0.0 &
      resultSimplePrice.twenty_four_hr_change != 0.0 &
      resultSimplePrice.last_updated_at != 0.0)
  }

}
