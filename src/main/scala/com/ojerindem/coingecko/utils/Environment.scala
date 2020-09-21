package com.ojerindem.coingecko.client {
  private [client] case class Ping(gecko_says: String)
  case class ApiAddress(address: String)
  private [client] case class SimplePriceHelper(coin_id: String,
                                          vs_currency: String,
                                          price_in_currency: Double,
                                          market_cap: String,
                                          twenty_four_hr_volume: String,
                                          twenty_four_hr_change: String,
                                          last_updated_at: String,
                                          contract_address: String = "n/a")
  private [client] case class SimplePrice(coin_id: String,
                                          vs_currency: String,
                                          price_in_currency: Double,
                                          market_cap: String,
                                          twenty_four_hr_volume: String,
                                          twenty_four_hr_change: String,
                                          last_updated_at: String,
                                          contract_address: String = "n/a")
  private [client] case class SimpleTokenPrice(coin_id: String,
                                               contract_address: String,
                                               vs_currency: String,
                                               price_in_currency: Double,
                                               market_cap: String,
                                               twenty_four_hr_volume: String,
                                               twenty_four_hr_change: String,
                                               last_updated_at: String )
}



