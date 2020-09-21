import com.typesafe.scalalogging.Logger
package com.ojerindem.coingecko.client {
  trait Logging {
    // Implicit logger used throughout the application
    // TODO: Ensure the calling Class is referenced in the stdout
    private [client] implicit val logger = Logger("logger")
  }
}
