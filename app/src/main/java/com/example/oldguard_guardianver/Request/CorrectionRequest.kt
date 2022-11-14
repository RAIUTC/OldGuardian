package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

//보호자 수정 정보(성함, 연락처)
data class CorrectionRequest(
    @SerializedName("name") val editCorrectName: String?,
    @SerializedName("contact") val editCorrectNumber: String?
)
