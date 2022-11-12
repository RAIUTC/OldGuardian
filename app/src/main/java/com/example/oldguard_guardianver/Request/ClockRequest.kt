package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName
import java.time.LocalTime

data class ClockRequest(
    @SerializedName("sleepStartTime") val startTime: String?,
    @SerializedName("sleepEndTime") val endTime: String?
)
