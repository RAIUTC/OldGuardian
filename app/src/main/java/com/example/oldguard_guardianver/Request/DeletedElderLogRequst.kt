package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

data class DeletedElderLogRequst(
    @SerializedName("sort") val sort: String
)
