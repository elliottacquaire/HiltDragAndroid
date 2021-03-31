package com.exae.proandroid.bean

data class ResultBean<T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val code: String? = null
) {
    fun isSuccess(): Boolean = status == Status.SUCCESS

    fun isFail(): Boolean = status == Status.ERROR

//    fun isNetFail(): Boolean = message == Secrets.Api.ERROR.NET

//    fun isSocketTimeout(): Boolean = code == SOCKET_TIMEOUT

    companion object {
        fun <T> success(data: T?): ResultBean<T> {
            return ResultBean(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?, code: String?): ResultBean<T> {

            return ResultBean(Status.ERROR, data, msg, code)
        }

        fun <T> loading(data: T?): ResultBean<T> {
            return ResultBean(Status.LOADING, data, null)
        }
    }
}