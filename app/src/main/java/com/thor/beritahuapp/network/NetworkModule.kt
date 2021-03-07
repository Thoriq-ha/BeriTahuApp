package com.thor.beritahuapp.network

import com.thor.beritahuapp.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule{
    fun interceptor(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    companion object {

        fun configNetwork(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

           }

        fun service() : BeritaService{
//                return configNetwork(str).create(BeritaService::class.java)
            return configNetwork().create(BeritaService::class.java)

        }

    }
}