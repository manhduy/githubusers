package com.duyha.data.di

import com.duyha.data.network.api.GithubApi
import com.duyha.data.network.auth.AuthInterceptor
import com.duyha.network.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


/**
 * Network Module
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_DEFAULT = 60_000L
    const val AUTH_INTERCEPTOR = "named:auths_intercept"

    @Provides
    @Named(AUTH_INTERCEPTOR)
    fun provideAuthInterceptor(): Interceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        @Named(AUTH_INTERCEPTOR)
        authInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_DEFAULT, TimeUnit.MILLISECONDS)
            .readTimeout(TIMEOUT_DEFAULT, TimeUnit.MILLISECONDS)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val gson = GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideApi(
        client: OkHttpClient,
        converter: Converter.Factory
    ): GithubApi {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(converter)
            .baseUrl(GithubApi.BASE_URL)
            .build()
            .create(GithubApi::class.java)
    }
}