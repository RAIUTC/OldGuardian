package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName
//data class All(
//    @SerializedName("guestName") var guestName: String,
//    val allRequest: AllRequest,
//)
data class AllRequest(
    @SerializedName("guestName") var guestName: String,
    @SerializedName("name") var name: String,
    @SerializedName("phoneNumber") var phoneNumber:String
    //@SerializedName("timeLimit") val timeLimit: String?,
    //@SerializedName("sleepStartTime") val startTime: String?,
//    @SerializedName("sleepEndTime") val endTime: String?
)
