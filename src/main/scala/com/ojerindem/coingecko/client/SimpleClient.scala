import com.ojerindem.coingecko.utils.Exceptions.VsCurrencyNotFoundException
import com.ojerindem.coingecko.utils.Logging

package com.ojerindem.coingecko.client {

  /** Wraps the Simple API Calls */
  class SimpleClient(implicit apiAddress: ApiAddress) extends CoinGeckoHttp with Logging {

    /** Default API address with the simple call added */
    private val simpleAddr = apiAddress.address + "/simple"

    /**
     * Returns a JSON String of simple price information (Token/Coin)
     *
     * @param priceString Substring of API call
     * @param vsCurrency The currency to compare the 'Coin/Token' against
     * @param includeMktCap Boolean for Market Cap
     * @param include24hrVol Boolean for 24hr Volume data
     * @param include24hrChange Boolean for 24hr Change data
     * @param includeLastUpdatedAt Boolean for 'Last Updated At' Epoch time
     * @return Json string of API call
     */
    private [client] def simplePriceJsonHelper(priceString: String,
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

    /**
     * Returns a JSON String of the simple price for a given Cryptocurrency coin
     * @param id Cryptocurrency name
     * @param vsCurrency Currency to compare the ID against
     * @param includeMktCap Boolean for Market Cap
     * @param include24hrVol Boolean for 24hr Volume data
     * @param include24hrChange Boolean for 24hr Change data
     * @param includeLastUpdatedAt Boolean for 'Last Updated At' Epoch time
     * @return Json string of simple price API call
     */
    def simplePriceJson(id: String,
                        vsCurrency: String,
                        includeMktCap: Boolean = true,
                        include24hrVol: Boolean = true,
                        include24hrChange: Boolean = true,
                        includeLastUpdatedAt: Boolean = true) = {
      runIfValidVsCurrency(vsCurrency) {
        val priceString = s"/price?ids=$id"
        val response = simplePriceJsonHelper(priceString, vsCurrency, includeMktCap, include24hrVol, include24hrChange, includeLastUpdatedAt)
        response
      }
    }

    /**
     * Returns a JSON String of the simple token price for a given Cryptocurrency Token (Ethereum based)
     * @param contractAddress Token address for API query
     * @param vsCurrency Currency to compare the ID against
     * @param id Cryptocurrency name (Ethereum)
     * @param includeMktCap Boolean for Market Cap
     * @param include24hrVol Boolean for 24hr Volume data
     * @param include24hrChange Boolean for 24hr Change data
     * @param includeLastUpdatedAt Boolean for 'Last Updated At' Epoch time
     * @return Json string of simple price API call
     */
    def simpleTokenPriceJson(contractAddress: String,
                             vsCurrency: String,
                             id: String = "ethereum",
                             includeMktCap: Boolean = true,
                             include24hrVol: Boolean = true,
                             include24hrChange: Boolean = true,
                             includeLastUpdatedAt: Boolean = true) = {
      runIfValidVsCurrency(vsCurrency) {
        val tokenPriceString = s"/token_price/$id?contract_addresses=$contractAddress"
        val response = simplePriceJsonHelper(tokenPriceString,vsCurrency,includeMktCap,include24hrVol,include24hrChange,includeLastUpdatedAt)
        response
      }
    }

    /**
     * Returns an expr
     * @param vsCurrency The currency to validate
     * @param expr The expr to evaluate
     * @return The evaluated 'expr' as a string
     * @throws VsCurrencyNotFoundException
     */
    private [client] def runIfValidVsCurrency(vsCurrency: String)(expr: String) = {
      val supportedVsCurrenciesJson = simpleSupportedVsCurrenciesJson
      val validCurrency = supportedVsCurrenciesJson.contains(vsCurrency)
      validCurrency match {
        case true => expr
        case false => throw new VsCurrencyNotFoundException(s"the provided vsCurrency '$vsCurrency' does not exists in the list $supportedVsCurrenciesJson" +
          s"\nPlease use one of the supported currencies")

      }
    }

    /** Returns a Json string representation of the supported vs_currency list */
    def simpleSupportedVsCurrenciesJson = {
      val simpleSupportedVsCurrenciesAddr = simpleAddr + "/supported_vs_currencies"
      val response = getBody(simpleSupportedVsCurrenciesAddr)
      response
    }
  }

  object SimpleClient  {
    def apply(apiVersion: Int = 3): SimpleClient = {
      /** The default API passed into the Client. API Version is parsed into address here*/
      implicit def apiAddress = ApiAddress(s"https://api.coingecko.com/api/v$apiVersion")
      new SimpleClient()
    }
  }

}