package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class DeletedElderLogInfo(
    @SerializedName("localDateTime") val localDateTime: String,
    @SerializedName("name") val name: String
)
