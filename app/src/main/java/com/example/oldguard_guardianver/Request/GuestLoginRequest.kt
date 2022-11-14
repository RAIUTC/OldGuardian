package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

//어르신 정보(로그인코드, 성함)
data class GuestLoginRequest(
    @SerializedName("code") val code: String?,
    @SerializedName("name") val name: String?
)