package com.exae.proandroid.requestData

import com.google.gson.annotations.SerializedName

const val CLIENT_GROUP = "DRS33043"
const val DEVICE_TYPE = "ANDROID"
const val GATEWAY = "pdc-api-gateway"
const val API_VERSION = "v1"
const val DRS_MOBILE_BUSINESS_NAME = "drs-mobile-bff"
const val DRS_MOBILE_TEST_DRIVE_NAME = "drs-customer-service"
const val DRS_FLOW_SERVICE_NAME = "drs-flow-service"
const val POS_SALES_BUSINESS_NAME = "drs-sales-service"
const val BUSINESS_PATH = "/$GATEWAY/$DRS_MOBILE_BUSINESS_NAME/$API_VERSION"

open class BaseRequest(@Transient open var path: String)

data class VerificationCodeLoginRequest(
    @SerializedName("phone")
    var phone: String = "",
    @SerializedName("verifyCode")
    var verify_code: String = ""
): BaseRequest("$BUSINESS_PATH/mbff/sms/token")
