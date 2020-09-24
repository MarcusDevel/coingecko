package com.ojerindem.coingecko.utils

import com.typesafe.scalalogging.Logger

trait Logging {
  
  implicit val logger  = Logger("logger")

}
