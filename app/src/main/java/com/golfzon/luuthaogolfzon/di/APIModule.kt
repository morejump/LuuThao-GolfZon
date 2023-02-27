package com.golfzon.luuthaogolfzon.di

import com.golfzon.luuthaogolfzon.model.PexelsAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class APIModule {
    private val API_KEY = "563492ad6f9170000100000130caaa9695e046bbba26d3f9464e8d82"
    private val BASE_URL = "https://api.pexels.com/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    @Provides
    fun providePhotosAPI(): PexelsAPI {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(PexelsAPI::class.java)
    }
}