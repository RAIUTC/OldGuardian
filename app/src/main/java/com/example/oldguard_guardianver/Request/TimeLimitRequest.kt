package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

//어르신 생활패턴(문자주는 시간)
data class TimeLimitRequest(
    @SerializedName("timeLimit") val code: Long?
)
