package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class TimeLimitRequest(
    @SerializedName("timeLimit") val code: Long?
)
