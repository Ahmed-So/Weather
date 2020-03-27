package com.ahmedSo.weather.data.remote.responses

import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("cod")
    val statusCode: String? = null

    @SerializedName("message")
    val message: String? = null

    val isSuccessful: Boolean
        get() = statusCode != null && statusCode == SUCCESS_STATUS_CODE

    companion object {
        private const val SUCCESS_STATUS_CODE = "200"
    }
}