package com.exae.proandroid.repository

import com.exae.proandroid.animation.ApiServiceAnno
import com.exae.proandroid.animation.RetrofitAnno
import com.exae.proandroid.api.NetwrokService
import com.exae.proandroid.bean.LoginResultModel
import com.exae.proandroid.bean.ProtocolResponse
import com.exae.proandroid.bean.ResultBean
import com.exae.proandroid.requestData.VerificationCodeLoginRequest
import com.orhanobut.logger.Logger
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit
import javax.inject.Inject

@ActivityScoped
class TestRepository  @Inject constructor(@RetrofitAnno var retrofit : Retrofit,@ApiServiceAnno var apiService : NetwrokService) {

    fun test() {
        Logger.d("petterp", "一个测试方法")
    }

    suspend fun verificationCodeLogin(request: VerificationCodeLoginRequest) : ResultBean<ProtocolResponse<LoginResultModel>> {
        return ResultBean.success(retrofit.create(NetwrokService::class.java).codeLoginRequest(request.path, request.verify_code,request.phone))
    }


    suspend fun verificationCodeLoginOther(request: VerificationCodeLoginRequest) : ResultBean<ProtocolResponse<LoginResultModel>> {
        return ResultBean.success(apiService.codeLoginRequest(request.path, request.verify_code,request.phone))
    }

}