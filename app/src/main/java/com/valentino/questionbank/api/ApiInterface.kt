package com.valentino.questionbank.api

import com.valentino.questionbank.model.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://d4def662.ngrok.io/"

interface ApiInterface {

    @GET("/schools")
    fun getSchools() : Call<List<School>>

    @GET("/schools/{id}/class")
    fun getSchoolClasses(@Path("id") id: Int)

    @POST("/users/register")
    fun registerUser(@Body user: User)

    @GET("/sessions/isvalid")
    fun tokenIsValid(@Header("user_id") token: String)

    @POST("/sessions/login")
    fun loginUser(@Body user: User)

    @POST("/sessions/logout")
    fun logoutUser(@Body user: User) : Call<Void>

    @POST("/classes")
    fun postClass(@Header("AuthToken") token: String) // user_id:token

    @GET("/classes")
    fun getClasses(@Header("AuthToken") token: String)

    @GET("/classes/folders")
    fun getFolders(@Header("AuthToken") token: String, @Query("class_id") classId: Int)

    @POST("/classes/folders")
    fun postFolder(@Header("AuthToken") token: String, @Body course: Course)

    @GET("/folders/questions")
    fun getQuestions(@Header("AuthToken") token: String, @Query("folder_id") folderId: Int)

    @POST("/folders/questions")
    fun postQuestion(@Header("AuthToken") token: String, @Body question: Question)

    @POST("/questions")
    fun postAnswer(@Header("AuthToken") token: String, @Body studentAnswer: StudentAnswer)

    @GET("/questions/rationale")
    fun getRationales(@Header("AuthToked") token: String, @Query("question_id") questionId: Int, @Query("class_id") classId: Int)

    companion object Factory {
        val BASE_URL = ""
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}