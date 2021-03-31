package com.exae.proandroid.viewmodel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.exae.proandroid.base.errorHandle
import com.exae.proandroid.base.launch
import com.exae.proandroid.bean.LoginResultModel
import com.exae.proandroid.bean.ProtocolResponse
import com.exae.proandroid.bean.ResultBean
import com.exae.proandroid.repository.TestRepository
import com.exae.proandroid.requestData.VerificationCodeLoginRequest
import dagger.hilt.android.qualifiers.ApplicationContext

class TestViewModel @ViewModelInject constructor(
    private val repository: TestRepository,
    @ApplicationContext var context: Context
) : ViewModel() {

    fun test() {
        repository.test()
    }

    var loginCodeResponse = MutableLiveData<ResultBean<ProtocolResponse<LoginResultModel>>>()
    fun codeLoginRequest(phone: String, code: String) {

        val verificationCodeLoginRequest = VerificationCodeLoginRequest(phone,code)
//        setUserPhone(phone)

//        state.verificationCodeLoginRequest.also {
//            it.phone = phone
//            it.verify_code = code
//        }
        launch(
            {
                //第一种写法 适应于不同的 NetwrokService
//                loginCodeResponse.value =
//                    repository.verificationCodeLogin(verificationCodeLoginRequest)

                //第二种写法，适应于同一个 NetwrokService
                loginCodeResponse.value =
                    repository.verificationCodeLoginOther(verificationCodeLoginRequest)

            },
            {
                loginCodeResponse.value = errorHandle(it)
            }
        )
    }


}