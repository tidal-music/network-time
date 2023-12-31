package com.tidal.networktime.internal

internal class SyncDisable(private val mutableState: MutableState) : () -> Unit {
  override operator fun invoke() = with(mutableState) {
    val job = job ?: return
    if (!job.isCancelled) {
      job.cancel()
    }
    this.job = null
  }
}
