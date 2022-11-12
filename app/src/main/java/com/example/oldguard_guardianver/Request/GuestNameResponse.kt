package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class GuestNameResponse(
    @SerializedName("name") val name: String
)
