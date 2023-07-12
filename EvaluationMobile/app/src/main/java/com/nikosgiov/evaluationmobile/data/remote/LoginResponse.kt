package com.nikosgiov.evaluationmobile.data.remote

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("access_token") val accessToken: String
)