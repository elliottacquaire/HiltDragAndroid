package com.exae.proandroid.bean

import com.google.gson.annotations.SerializedName

class LoginResultModel(
    @SerializedName("refreshToken")
    val refreshToken: String?,
    @SerializedName("userId")
    val userId: String?,
    @SerializedName("roleList")
    val roles: Array<String>?,
    @SerializedName("accessToken")
    var accessToken: String = ""
)