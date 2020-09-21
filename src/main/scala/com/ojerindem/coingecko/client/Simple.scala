package com.ojerindem.coingecko.client

import io.circe._
import io.circe.parser._

/** Wraps the Simple API Calls */
class Simple(implicit apiAddress: ApiAddress) extends CoinGeckoHttp with Logging {

  /** Default API address with the simple call added */
  private val simpleAddr = apiAddress.address + "/simple"

  /**
   * Returns a JSON String of simple price information for a given coin/currency pair
   *
   * @param priceString The ID of the cryptocurrency to search price info for
   * @param vsCurrency The currency to compare the 'id' against
   * @param includeMktCap Boolean for Market Cap
   * @param include24hrVol Boolean for 24hr Volume data
   * @param include24hrChange Boolean for 24hr Change data
   * @param includeLastUpdatedAt Boolean for 'Last Updated At' Epoch time
   * @return Json string of simple price API call
   *
   *         Example:
   *         {
   *         "ethereum": {
   *             "btc": 0.0340046,
   *             "btc_market_cap": 3831591.394660505,
   *             "btc_24h_vol": 989774.1440358246,
   *             "btc_24h_change": -2.31968432833714,
   *             "last_updated_at": 1600634561
   *         }
   *         }
   */
  def simplePriceJsonHelper(priceString: String,
                      vsCurrency: String,
                      includeMktCap: Boolean = true,
                      include24hrVol: Boolean = true,
                      include24hrChange: Boolean = true,
                      includeLastUpdatedAt: Boolean = true) = {
    val simplePriceAddr =
      simpleAddr +
        s"${priceString}&vs_currencies=$vsCurrency&include_market_cap=$includeMktCap" +
        s"&include_24hr_vol=$include24hrVol&include_24hr_change=$include24hrChange&include_last_updated_at=$includeLastUpdatedAt"

    val response = getBody(simplePriceAddr)
    response
  }

  def simplePriceJson(id: String,
                      vsCurrency: String,
                      includeMktCap: Boolean = true,
                      include24hrVol: Boolean = true,
                      include24hrChange: Boolean = true,
                      includeLastUpdatedAt: Boolean = true) = {
    val priceString = s"/price?ids=$id"
    val response = simplePriceJsonHelper(priceString,vsCurrency,includeMktCap,include24hrVol,include24hrChange,includeLastUpdatedAt)
    response
  }

  def simpleTokenPriceJson(contractAddress: String,
                           vsCurrency: String,
                           id: String = "ethereum",
                           includeMktCap: Boolean = true,
                           include24hrVol: Boolean = true,
                           include24hrChange: Boolean = true,
                           includeLastUpdatedAt: Boolean = true) = {
    val tokenPriceString = s"/token_price/$id?contract_addresses=$contractAddress"
    val response = simplePriceJsonHelper(tokenPriceString,vsCurrency,includeMktCap,include24hrVol,include24hrChange,includeLastUpdatedAt)
    response
  }

  /**
   * Returns a formatted case class SimplePrice for a given coin/currency pair
   *
   * @param id The ID of the cryptocurrency to search price info for
   * @param vsCurrency The currency to compare the 'id' against
   * @param includeMktCap Boolean for Market Cap
   * @param include24hrVol Boolean for 24hr Volume data
   * @param include24hrChange Boolean for 24hr Change data
   * @param includeLastUpdatedAt Boolean for 'Last Updated At' Epoch time
   * @return SimplePrice Case Class
   *
   *         Example: SimplePrice(ethereum,gbp,285.16,3.1970109395524216E10,8.598529050538557E9,-4.225233634469001,1.60064216E9)
   *
   */
  def simplePriceHelper(responseJson: String,
                         id: String,
                  vsCurrency: String,
                  includeMktCap: Boolean = true,
                  include24hrVol: Boolean = true,
                  include24hrChange: Boolean = true,
                  includeLastUpdatedAt: Boolean = true,
                  contractAddress: String = "n/a") = {

    implicit class RichEither(a: Either[DecodingFailure, Double])  {
      def isFalse(someBool: Boolean): String = {
        a.getOrElse(if (someBool) "0.0" else "false").toString
      }
    }

    val response = responseJson
    val doc: Json = parse(response).getOrElse(Json.Null)
    val cursor: ACursor = doc.hcursor.downField(s"${if (contractAddress == "n/a") id else contractAddress}")


    SimplePriceHelper(
        id,
        vsCurrency,
      cursor
        .downField(s"${vsCurrency}").as[Double].getOrElse(0.0),
      cursor
      .downField(s"${vsCurrency}_market_cap").as[Double].isFalse(includeMktCap),
      cursor
        .downField(s"${vsCurrency}_24h_vol").as[Double].isFalse(include24hrVol),
      cursor
        .downField(s"${vsCurrency}_24h_change").as[Double].isFalse(include24hrChange),
      cursor
        .downField(s"last_updated_at").as[Double].isFalse(includeLastUpdatedAt),
      contractAddress)
  }

  /**
   * {
   * "0x0bc529c00c6401aef6d220be8c6ea1667f6ad93e": {
   * "gbp": 18427.1,
   * "gbp_market_cap": 552306657.2391405,
   * "gbp_24h_vol": 523819496.7416337,
   * "gbp_24h_change": -13.573455176283511,
   * "last_updated_at": 1600717456
   * }
   * }
   */

  def simplePrice(id: String = "ethereum",
                  vsCurrency: String,
                  includeMktCap: Boolean = true,
                  include24hrVol: Boolean = true,
                  include24hrChange: Boolean = true,
                  includeLastUpdatedAt: Boolean = true) = {
    val response = simplePriceJson(id,vsCurrency,includeMktCap,include24hrVol,include24hrChange,includeLastUpdatedAt)
    val wrappedResult = simplePriceHelper(response,id,vsCurrency,includeMktCap,include24hrVol,include24hrChange,includeLastUpdatedAt)
    SimplePrice(wrappedResult.coin_id,wrappedResult.vs_currency,wrappedResult.price_in_currency,wrappedResult.market_cap,wrappedResult.twenty_four_hr_volume,wrappedResult.twenty_four_hr_change,wrappedResult.last_updated_at)
  }

  def simpleTokenPrice(contractAddress: String,
                       vsCurrency: String,
                       id: String = "ethereum",
                       includeMktCap: Boolean = true,
                       include24hrVol: Boolean = true,
                       include24hrChange: Boolean = true,
                       includeLastUpdatedAt: Boolean = true) = {
    val response = simpleTokenPriceJson(contractAddress,vsCurrency,id,includeMktCap,include24hrVol,include24hrChange,includeLastUpdatedAt)
    val wrappedResult = simplePriceHelper(response,id,vsCurrency,includeMktCap,include24hrVol,include24hrChange,includeLastUpdatedAt,contractAddress)
    SimpleTokenPrice(wrappedResult.coin_id,wrappedResult.contract_address,wrappedResult.vs_currency,wrappedResult.price_in_currency,wrappedResult.market_cap,wrappedResult.twenty_four_hr_volume,wrappedResult.twenty_four_hr_change,wrappedResult.last_updated_at)

  }

}
