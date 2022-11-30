package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class RestoreGuestInfoRequest(
    @SerializedName("id") val id: Long
)
