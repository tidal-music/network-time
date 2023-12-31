package com.tidal.networktime.internal

import kotlin.time.Duration

internal data class NTPPacket(
  val leapIndicator: Int = 0,
  val versionNumber: Int,
  val mode: Int,
  val stratum: Int = 0,
  val poll: Duration = Duration.INFINITE,
  val precision: Duration = Duration.INFINITE,
  val rootDelay: Duration = Duration.INFINITE,
  val rootDispersion: Duration = Duration.INFINITE,
  val referenceIdentifier: String = "",
  val referenceEpochTimestamp: NTPTimestamp = NTPTimestamp(Duration.ZERO),
  val originateEpochTimestamp: NTPTimestamp = NTPTimestamp(Duration.ZERO),
  val receiveEpochTimestamp: NTPTimestamp = NTPTimestamp(Duration.ZERO),
  /** Keep this mutable to minimize delay (avoids an allocation) **/
  var transmitEpochTimestamp: NTPTimestamp = NTPTimestamp(Duration.ZERO),
) {
  companion object {
    const val NTP_TIMESTAMP_BASE_WITH_EPOCH_MSB_0_MILLISECONDS = 2085978496000
    const val NTP_TIMESTAMP_BASE_WITH_EPOCH_MSB_1_MILLISECONDS = -2208988800000
  }
}
