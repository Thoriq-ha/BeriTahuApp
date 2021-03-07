package com.thor.beritahuapp.network

import com.thor.beritahuapp.model.BeritaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface BeritaService {

    @GET("everything?q=tesla&from=2021-02-07&sortBy=publishedAt&apiKey=1cd906d561f34bcda0ba8088d9765da5")
    fun getKategoriTesla(): Call<BeritaResponse>

    @GET("everything?q=apple&from=2021-03-06&to=2021-03-06&sortBy=popularity&apiKey=1cd906d561f34bcda0ba8088d9765da5")
    fun getKategoriApple(): Call<BeritaResponse>

    @GET("top-headlines?country=us&category=business&apiKey=1cd906d561f34bcda0ba8088d9765da5")
    fun getKategoriUs(): Call<BeritaResponse>

    @GET("top-headlines?sources=techcrunch&apiKey=1cd906d561f34bcda0ba8088d9765da5")
    fun getKategoriTechCrunch(): Call<BeritaResponse>

}