package com.ojerindem.coingecko.utils

import org.apache.log4j.{BasicConfigurator, Logger}
trait Logging {

  def configureLogging = BasicConfigurator.configure()
  // Implicit logger used throughout the application
  // TODO: Ensure the calling Class is referenced in the stdout
  implicit val logger: Logger = Logger.getLogger(getClass)

}
