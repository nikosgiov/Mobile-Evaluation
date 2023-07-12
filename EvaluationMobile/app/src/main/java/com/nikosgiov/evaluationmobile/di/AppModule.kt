package com.nikosgiov.evaluationmobile.di

import ApiService
import ApiServiceRepository
import com.nikosgiov.evaluation.domain.use_case.login.PasswordUseCase
import com.nikosgiov.evaluationmobile.common.Constants.BASE_URL
import com.nikosgiov.evaluationmobile.data.repository.ApiServiceRepositoryImpl
import com.nikosgiov.evaluationmobile.domain.login.SignInUseCase
import com.nikosgiov.evaluationmobile.domain.login.UserIDUseCase
import com.nikosgiov.evaluationmobile.domain.usecase.FetchMagazinesUseCase
import com.nikosgiov.evaluationmobile.presentation.login.PasswordViewModel
import com.nikosgiov.evaluationmobile.presentation.login.SignInViewModel
import com.nikosgiov.evaluationmobile.presentation.login.UserIDViewModel
import com.nikosgiov.evaluationmobile.presentation.magazines.MagazineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build() }
    single { get<Retrofit>().create(ApiService::class.java) }
    single<ApiServiceRepository> { ApiServiceRepositoryImpl(get()) }

    single { UserIDUseCase() }
    single { PasswordUseCase() }
    single { SignInUseCase(get()) }
    single { FetchMagazinesUseCase(get()) }
    viewModel { UserIDViewModel(get()) }
    viewModel { PasswordViewModel(get()) }
    viewModel { SignInViewModel(get(), get(), get()) }
    viewModel { MagazineViewModel(get()) }
}