package com.tidal.networktime.internal

import kotlinx.datetime.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

internal class KotlinXDateTimeSystemClock {
  val referenceEpochTime: Duration
    get() = Clock.System.now().toEpochMilliseconds().milliseconds
}
