package com.ojerindem.coingecko.utils

object Exceptions {
  class UnknownApiPathException(s:String) extends Exception(s){}
  class VsCurrencyNotFoundException(s:String) extends Exception(s){}
}
