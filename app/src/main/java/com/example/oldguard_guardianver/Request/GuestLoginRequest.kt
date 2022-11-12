package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class GuestLoginRequest(
    @SerializedName("code") val code: String?,
    @SerializedName("name") val name: String?
)