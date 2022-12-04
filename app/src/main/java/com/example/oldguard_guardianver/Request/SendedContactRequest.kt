package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class SendedContactRequest(
    @SerializedName("sort") val sort: String
)
