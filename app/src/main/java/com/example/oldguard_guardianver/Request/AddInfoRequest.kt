package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

//보호자 정보(성함, 전화번호 data)
data class AddInfoRequest(
    @SerializedName("name") val name: String?,
    @SerializedName("contact") val number: String?

)
