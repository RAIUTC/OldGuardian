package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class DeleteGuestInfoRequest(
    @SerializedName("id") val id: Long
)
