package com.valentino.questionbank.api

import com.valentino.questionbank.model.School
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/schools")
    fun getSchools() : Call<List<School>>


}