package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class AddInfoRequest(
    @SerializedName("name") val name: String?,
    @SerializedName("contact") val number: String?

)
