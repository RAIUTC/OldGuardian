package com.example.oldguard_guardianver

import com.example.oldguard_guardianver.Request.*
import retrofit2.Call
import retrofit2.http.*


interface HowIService {
    @GET("/api/v1/guest/name")
    fun getResponse(
        @Query("name") name : GuestNameResponse
    ): Call<String>

    @GET("/api/v1/guest")
    fun getLoginRequest(
        @Query("guestName") guestName: AllRequest,
        @Query("name") name :AllRequest,
        @Query("phoneNumber") phoneNumber: AllRequest,
    ): Call<AllRequest>

    @FormUrlEncoded
    @POST("/login")
    fun postLoginRequest(@Field("token") token: String): Call<String>

    //코드 이름 추가
    @POST("/api/v1/member/guest")
    fun postGuestLoginRequest(
        @Body request: GuestLoginRequest
    ): Call<String>

    //이름 연락 추가
    @POST("/api/v1/contact")
    fun postAddInfoRequest(
        @Body request: AddInfoRequest
    ): Call<String>

    //이름 연락 추가
    @POST("/api/v1/contact")
    fun postCorrectionRequest(
        @Body request: CorrectionRequest
    ): Call<String>

    //자는시간 일어나는시간 추가
    @PATCH("/api/v1/guest/sleep")
    fun postClockRequest(
        @Body request: ClockRequest
    ): Call<String>

    @POST("/api/v1/guest/limit")
    fun postTimeLimitRequest(
        @Body request: TimeLimitRequest
    ): Call<String>


}