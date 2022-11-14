package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

//이름 받기
data class GuestNameResponse(
    @SerializedName("name") val name: String
)
