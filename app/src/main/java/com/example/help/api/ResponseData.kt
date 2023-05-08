package com.example.help.api

import com.google.gson.annotations.SerializedName

/**
 * @author yuanan
 * @date 2023/5/8
 * @desc 基本响应
 */
data class ResponseData<T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T
)
