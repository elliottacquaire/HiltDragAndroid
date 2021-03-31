package com.exae.proandroid.bean

import com.google.gson.annotations.SerializedName

class ProtocolResponse<T> {
    @SerializedName("message")
    val message: String? = null

    @SerializedName("code")
    val code: String? = null

    @SerializedName("data")
    val data: T? = null
}