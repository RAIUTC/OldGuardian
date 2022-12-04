package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class SendedContactLogInfo(
    @SerializedName("localDateTime") val localDateTime: String,
    @SerializedName("name") val name: String
)
