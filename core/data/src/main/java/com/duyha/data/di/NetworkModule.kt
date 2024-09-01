package com.duyha.data.di

import com.duyha.data.remote.api.GithubApi
import com.duyha.data.remote.header.HeaderInterceptor
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
 * Hilt module for providing network dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_DEFAULT = 60_000L
    private const val HEADER_INTERCEPTOR = "named:header_intercept"

    /**
     * Provides the header interceptor.
     * @return The header interceptor.
     */
    @Provides
    @Named(HEADER_INTERCEPTOR)
    fun provideAuthInterceptor(): Interceptor {
        return HeaderInterceptor()
    }

    /**
     * Provides the OkHttpClient.
     * @param headerInterceptor The header interceptor.
     * @return The OkHttpClient.
     */
    @Provides
    @Singleton
    fun provideHttpClient(
        @Named(HEADER_INTERCEPTOR)
        headerInterceptor: Interceptor,
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
            .addInterceptor(headerInterceptor)
            .build()
    }

    /**
     * Provides the Gson converter factory.
     * @return The converter factory.
     */
    @Provides
    fun provideGsonConverterFactory(): Converter.Factory {
        val gson = GsonBuilder()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
        return GsonConverterFactory.create(gson)
    }

    /**
     * Provides the GitHub API.
     * @param client The OkHttpClient.
     * @param converter The converter factory.
     * @return The GitHub API.
     **/
    @Provides
    @Singleton
    fun provideGitHubApi(
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