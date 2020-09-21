package com.ojerindem.coingecko.client {
  private [client] case class Ping(gecko_says: String)
  case class ApiAddress(address: String)
  private [client] case class SimplePrice(coin_id: String,
                                          vs_currency: String,
                                          price_in_currency: Double,
                                          market_cap: Double,
                                          twenty_four_hr_volume: Double,
                                          twenty_four_hr_change: Double,
                                          last_updated_at: Double )
}



