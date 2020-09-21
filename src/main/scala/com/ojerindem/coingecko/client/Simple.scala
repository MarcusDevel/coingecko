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

}
