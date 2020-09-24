package com.ojerindem.coingecko.client

import com.ojerindem.coingecko.utils.Logging

class CoinsClient(implicit apiAddress: ApiAddress) extends CoinGeckoHttp with Logging {

  private val coinsAddr = apiAddress.address + "/coins"

  /** Return the list of all supported coin by id,symbol,name */
  def coinsList = {
    val coinsListAddr = coinsAddr + "/list"
    val response = getBody(coinsListAddr)
    response
  }

  /**
   * Returns ALL coin data available from the API:
   * id,
   * symbol,
   * name,
   * image,
   * current_price,
   * market_cap,
   * market_cap_rank,
   * fully_diluted_valuation,
   * total_volume,
   * high_24h,
   * low_24h,
   * price_change_24h,
   * price_change_percentage_24h,
   * market_cap_change_24h,
   * market_cap_change_percentage_24h,
   * circulating_supply,
   * total_supply,
   * max_supply,
   * ath,
   * ath_change_percentage,
   * ath_date,
   * atl,
   * atl_change_percentage,
   * atl_date,
   * roi,
   * last_updated,
   * price_change_percentage_24h_in_currency,
   *
   * @param vsCurrency: Currency data will be priced in
   * @param ids Is of the coins
   * @param category Coin category (Defaults to "decentralized_finance_defi" at the moment as only available)
   * @param order Defines the order to rank data
   *              (Option are: market_cap_desc, gecko_desc, gecko_asc, market_cap_asc,
   *               market_cap_desc, volume_asc, volume_desc, id_asc, id_desc)
   * @param perPage How many items to show per page (Currently broken)
   * @param page Which page of data to display
   * @param sparkLine Include 7 day sparkline data (true/false)
   * @param priceChangePercentage Include price change percentage in 1h, 24h, 7d, 14d, 30d, 200d, 1y
   *                              (eg. '1h,24h,7d' comma-separated, invalid values will be discarded)
   * @return
   */
  def coinsMarkets(vsCurrency: String,
                   ids: String,
                   category: String = "decentralized_finance_defi",
                   order: String = "market_cap_desc",
                   perPage: String = "1",
                   page: String = "1",
                   sparkLine: Boolean = false,
                   priceChangePercentage: String = "24h") = {
    val apiString = coinsAddr +
      s"/markets?vs_currency=$vsCurrency&ids=$ids&category=$category&order=$order" +
      s"&per_page=$perPage&page=$page&sparkline=$sparkLine&price_change_percentage=$priceChangePercentage"

    val response = getBody(apiString)
    response
  }



}

object CoinsClient  {
  def apply(apiVersion: Int = 3): CoinsClient = {
    /** The default API passed into the Client. API Version is parsed into address here*/
    implicit def apiAddress = ApiAddress(s"https://api.coingecko.com/api/v$apiVersion")
    new CoinsClient()
  }
}
