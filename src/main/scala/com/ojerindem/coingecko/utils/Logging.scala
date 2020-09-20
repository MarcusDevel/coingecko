package com.ojerindem.coingecko.utils
import com.typesafe.scalalogging.Logger

trait Logging {
  // Implicit logger used throughout the application
  // TODO: Ensure the calling Class is referenced in the stdout
  implicit val logger = Logger("logger")
}
