package com.nikosgiov.evaluationmobile.data.repository

import ApiService
import ApiServiceRepository
import android.util.Log
import com.nikosgiov.evaluationmobile.common.Constants.BASE_URL
import com.nikosgiov.evaluationmobile.common.Resource
import com.nikosgiov.evaluationmobile.data.remote.LoginRequest
import com.nikosgiov.evaluationmobile.domain.model.LoginResult
import com.nikosgiov.evaluationmobile.domain.model.Magazine
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServiceRepositoryImpl(private val authService: ApiService) : ApiServiceRepository {
    override suspend fun login(userId: String, password: String): LoginResult {
        try {
            val loginRequest = LoginRequest(userName = userId, password = password)
            val loginResponse = authService.login(loginRequest)
            return LoginResult.Success(loginResponse.accessToken, loginResponse.tokenType)
        } catch (e: Exception) {
            Log.e("Login Error", "Error occurred: ${e.message}", e)
            throw e
        }
    }

    override suspend fun getMagazines(
        accessToken: String,
        tokenType: String
    ): Resource<List<Magazine>> {
        try {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .header("Authorization", "$tokenType $accessToken")
                        .build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val magazineService = retrofit.create(ApiService::class.java)
            val magazines = magazineService.getMagazines()
            return Resource.Success(magazines)
        } catch (e: Exception) {
            val errorMessage = e.message ?: "Unknown error"
            return Resource.Error(errorMessage)
        }
    }
}
