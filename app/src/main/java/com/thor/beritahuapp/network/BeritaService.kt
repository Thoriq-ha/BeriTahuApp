package com.thor.beritahuapp.network

import com.thor.beritahuapp.model.BeritaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface BeritaService {


    @GET ("top-headlines?country=us&category=business&apiKey=1cd906d561f34bcda0ba8088d9765da5")
    fun getData() : Call<BeritaResponse>
//    @GET("&apiKey=1cd906d561f34bcda0ba8088d9765da5")
//    fun getKategoriTesla(): Call<BeritaResponse>
//
//    @GET("&apiKey=1cd906d561f34bcda0ba8088d9765da5")
//    fun getKategoriApple(): Call<BeritaResponse>
//
//    @GET("top-headlines?country=us&category=business&apiKey=1cd906d561f34bcda0ba8088d9765da5")
//    fun getKategoriUs(): Call<BeritaResponse>
//
//    @GET("top-headlines?sources=techcrunch&apiKey=1cd906d561f34bcda0ba8088d9765da5")
//    fun getKategoriTechCrunch(): Call<BeritaResponse>

}