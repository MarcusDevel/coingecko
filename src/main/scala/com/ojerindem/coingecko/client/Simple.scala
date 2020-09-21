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
   * @param id The ID of the cryptocurrency to search price info for
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
  def simplePriceJson(id: String,
                      vsCurrency: String,
                      includeMktCap: String,
                      include24hrVol: String,
                      include24hrChange: String,
                      includeLastUpdatedAt: String) = {
    val simplePriceAddr =
      simpleAddr +
        s"/price?ids=$id&vs_currencies=$vsCurrency&include_market_cap=$includeMktCap" +
        s"&include_24hr_vol=$include24hrVol&include_24hr_change=$include24hrChange&include_last_updated_at=$includeLastUpdatedAt"

    val response = getBody(simplePriceAddr)
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
  def simplePrice(id: String,
                  vsCurrency: String,
                  includeMktCap: String,
                  include24hrVol: String,
                  include24hrChange: String,
                  includeLastUpdatedAt: String) = {
    val response = simplePriceJson(id,vsCurrency,includeMktCap,include24hrVol,include24hrChange,includeLastUpdatedAt)
    val doc: Json = parse(response).getOrElse(Json.Null)
    val cursor: ACursor = doc.hcursor.downField(s"${id}")
    SimplePrice(
        id,
        vsCurrency,
      cursor
        .downField(s"${vsCurrency}").as[Double].getOrElse(0.0),
      cursor
      .downField(s"${vsCurrency}_market_cap").as[Double].getOrElse(0.0),
      cursor
        .downField(s"${vsCurrency}_24h_vol").as[Double].getOrElse(0.0),
      cursor
        .downField(s"${vsCurrency}_24h_change").as[Double].getOrElse(0.0),
      cursor
        .downField(s"last_updated_at").as[Double].getOrElse(0.0))
  }

}
