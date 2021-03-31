package com.exae.proandroid.api

import com.exae.proandroid.bean.LoginResultModel
import com.exae.proandroid.bean.ProtocolResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface NetwrokService {

    @FormUrlEncoded
    @POST("{path}")
    suspend fun codeLoginRequest(@Path(value = "path", encoded = true) url: String,
                                 @Field("verifyCode") verify_code : String,
                                 @Field("phone") phone : String

    ): ProtocolResponse<LoginResultModel>


}