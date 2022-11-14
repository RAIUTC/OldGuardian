package com.example.oldguard_guardianver.Request

import com.google.gson.annotations.SerializedName

//임시 데이터 클래스. 삭제 필요 DeletedRecordFragment의 recyclerView에서 사용 예정
data class DeletedRecordData(
    @SerializedName("name") val name: String,
    @SerializedName("time") val time: String
)
