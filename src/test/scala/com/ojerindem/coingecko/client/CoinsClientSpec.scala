package com.ojerindem.coingecko.client

import io.circe._, io.circe.parser._
import org.scalatest.flatspec.AnyFlatSpec

class CoinsClientSpec extends AnyFlatSpec {

  behavior of "CoinsClient"

  it should "return the list of coins in coingecko as a JSON String" in {
    val result = CoinsClient().coinsList
    val parseJson = parse(result)
    val isJson = parseJson match {
      case Left(failure) => false
      case Right(json) => true
    }
    println(result)
    assert(isJson === true)
  }

}
