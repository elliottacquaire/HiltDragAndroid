package com.exae.proandroid.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exae.proandroid.bean.ResultBean
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okio.Buffer
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

//CoroutineExceptionHandler The exception is thrown by a coroutine that automatically throws exceptions (works with launch, not with async)
fun ViewModel.launch(
    block: suspend CoroutineScope.() -> Unit,
    onError: (e: Throwable) -> Unit = {},
    onComplete: () -> Unit = {}
) {
    viewModelScope.launch(CoroutineExceptionHandler { _, e -> onError(e) }) {
        try {
            block.invoke(this)
        } finally {
            onComplete()
        }
    }
}

fun <T> errorHandle(error: Throwable): ResultBean<T> {
    var code = "400"
    var message: String =
        if (error is UnknownHostException || error is ConnectException || error is SocketTimeoutException || error is IOException) {
            if (error is SocketTimeoutException) {
//                code = ApiResponse.SOCKET_TIMEOUT
            }
            "Secrets.Api.ERROR.NET"
        } else {
            error.message ?: "unknown error"
        }
    if (error is HttpException) {
        if (error.code() >= 300) {
            try {
                val inputStream = error.response()?.errorBody()?.byteStream()
                val buffer = Buffer()
                inputStream?.let { buffer.readFrom(it) }
                val contentType = error.response()?.errorBody()?.contentType()
                val charset: Charset =
                    contentType?.charset(StandardCharsets.UTF_8) ?: StandardCharsets.UTF_8
                val errorBody = buffer.clone().readString(charset)
//                val errorBean = Gson().fromJson(errorBody, ErrorBean::class.java)
//                message = errorBean.message ?: ""
            }catch (e: Exception){ }
            if (message.isEmpty()) {
                message = "服务开小差了，请重试"
            }
        }
        code = error.code().toString()
    }
    return ResultBean.error(message, null, code)
}

