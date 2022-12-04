package com.example.oldguard_guardianver

import com.example.oldguard_guardianver.Request.*
import retrofit2.Call
import retrofit2.http.*


interface HowIService {
    //auth-controller
    //카카오톡 로그인
    @FormUrlEncoded
    @POST("/login")
    fun postLoginRequest(@Field("token") token: String): Call<String>

    //code-controller
    //인증코드 전송

    //contact-controller
    //연락처정보조회
    @GET("/api/v1/contact")
    fun getContacts(
        @Query("id") id : Long
    ):Call<List<ContactResponse>>

    //연락처 등록
    @POST("/api/v1/contact")
    fun postAddInfoRequest(
        @Body request: AddInfoRequest
    ): Call<String>

    //연락처 삭제 //@DELETE("/api/v1/contact")
    @HTTP(method = "DELETE", path = "/api/v1/contact", hasBody = true)
    fun deleteContact(
        @Body request: DeleteContactRequest
    ): Call<String>

    //연락처 수정
    @PATCH("api/v1/contact")
    fun EditContact(
        @Body request: EditContactRequest
    ): Call<String>

    //guest-controller
    //게스트 정보 조회
    @GET("/api/v1/guest")
    fun getAllResponse(
        @Query("id") id: Long
    ): Call <GuestResponse>

    //게스트 회원 가입
    @POST("/api/v1/guest")
    fun postGuestLoginRequest(
        @Body request: GuestLoginRequest
    ): Call<String>

    //게스트 정보 수정
    @PUT("/api/v1/guest")
    fun EditGuestInfo(
        @Body request: EditGuestInfoRequest
    ):Call<String>

    //게스트 정보 삭제
    @HTTP(method = "DELETE", path = "/api/v1/guest", hasBody = true)
//    @DELETE()
    fun deleteGuestInfo(
        @Body request: DeleteGuestInfoRequest
    ): Call<String>

    //시간제한 설정
    @POST("/api/v1/guest/limit")
    fun postTimeLimitRequest(
        @Body request: TimeLimitRequest
    ): Call<String>

    //게스트목록조회
    @GET("/api/v1/guest/list")
    fun getResponse(): Call <List<GuestListResponse>>

    //게스트 정보 복구
    @PATCH("/api/v1/guest/restoration")
    fun restoreGuestInfo(
        @Body request: RestoreGuestInfoRequest
    ): Call<String>

    //수면시간 설정
    @PATCH("/api/v1/guest/sleep")
    fun postClockRequest(
        @Body request: ClockRequest
    ): Call<String>

    //member-controller
    //사용자 회원탈퇴
    @DELETE("/api/v1/member/me")
    fun userMembershipWithdrawal(

    ):Call<String>

    //record-controller
    //삭제한 어르신 기록 조회
    @HTTP(method = "GET", path = "/api/v1/record/delete", hasBody = true)
    //@GET("/api/v1/record/delete")
    fun getDeletedElderLog(
        @Body request: DeletedElderLogRequst
    ): Call <List<DeletedElderLogInfo>>

    //전송한 연락 조회
    @GET("/api/v1/record/send")
    fun getSendedCoatactLog(
        @Body request: SendedContactRequest
    ): Call <List<SendedContactLogInfo>>
}