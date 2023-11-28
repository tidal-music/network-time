package com.tidal.networktime.internal

import kotlin.time.Duration

internal class NTPExchanger(
  private val referenceClock: KotlinXDateTimeSystemClock,
  private val ntpPacketSerializer: NTPPacketSerializer,
  private val ntpPacketDeserializer: NTPPacketDeserializer,
) {
  operator fun invoke(
    name: String,
    queryTimeout: Duration,
    ntpVersion: UByte,
  ): NTPExchangeResult? {
    val ntpUdpSocketOperations = NTPUDPSocketOperations()
    return try {
      ntpUdpSocketOperations.prepareSocket(queryTimeout.inWholeMilliseconds)
      val ntpPacket = NTPPacket(versionNumber = ntpVersion.toInt(), mode = NTP_MODE_CLIENT)
      val requestTime = referenceClock.referenceEpochTime
      ntpPacket.transmitEpochTimestamp = EpochTimestamp(requestTime).asNTPTimestamp
      val buffer = ntpPacketSerializer(ntpPacket)
      ntpUdpSocketOperations.exchangePacketInPlace(
        buffer,
        name,
        NTP_PORT_NUMBER,
      )
      val returnTime = referenceClock.referenceEpochTime
      ntpPacketDeserializer(buffer)?.let { NTPExchangeResult(returnTime, it) }
    } catch (_: Throwable) {
      null
    } finally {
      ntpUdpSocketOperations.closeSocket()
    }
  }

  companion object {
    private const val NTP_MODE_CLIENT = 3
    private const val NTP_PORT_NUMBER = 123
  }
}
