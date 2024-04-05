package com.example.got.`interface`

import com.example.got.models.Books
import com.example.got.models.Characters
import com.example.got.models.House
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface{

    @GET("books")
    fun getBooks(): Call<List<Books>>

    @GET("characters?page=1&pageSize=50")
    fun getCharacters(): Call<List<Characters>>

    @GET("houses?page=1&pageSize=50")
    fun getHousesPage1(): Call<List<House>>

    @GET("houses?page=2&pageSize=50")
    fun getHousesPage2(): Call<List<House>>

    @GET("houses?page=3&pageSize=50")
    fun getHousesPage3(): Call<List<House>>

    @GET("houses?page=4&pageSize=50")
    fun getHousesPage4(): Call<List<House>>

    @GET("houses?page=5&pageSize=50")
    fun getHousesPage5(): Call<List<House>>

    @GET("houses?page=6&pageSize=50")
    fun getHousesPage6(): Call<List<House>>

    @GET("houses?page=7&pageSize=50")
    fun getHousesPage7(): Call<List<House>>

    @GET("houses?page=8&pageSize=50")
    fun getHousesPage8(): Call<List<House>>

    @GET("houses?page=9&pageSize=50")
    fun getHousesPage9(): Call<List<House>>
}