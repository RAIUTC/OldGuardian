package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class CorrectionRequest(
    @SerializedName("name") val editCorrectName: String?,
    @SerializedName("contact") val editCorrectNumber: String?
)
