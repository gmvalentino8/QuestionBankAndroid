package com.valentino.questionbank.api

import com.valentino.questionbank.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://d4def662.ngrok.io/"

interface ApiInterface {

    @GET("/schools")
    fun getSchools() : Call<List<School>>

    @GET("/schools/{id}/class")
    fun getSchoolClasses(@Path("id") id: Int) : Call<List<Course>>

    @POST("/users/register")
    fun registerUser(@Body user: User) : Call<Void>

    @GET("/sessions/isvalid")
    fun tokenIsValid(@Header("AuthToken") token: String) : Call<ValidResponse>

    @POST("/sessions/login")
    fun loginUser(@Body login: Login) : Call<Session>

    @POST("/sessions/logout")
    fun logoutUser(@Body user: User) : Call<Void>

    @POST("/classes/instructor")
    fun postClass(@Header("AuthToken") token: String, @Body course: Course) : Call<Void> // user_id:token

    @POST("/classes/student")
    fun joinClass(@Header("AuthToken") token: String, @Body course: Course) : Call<Void>

    @GET("/classes")
    fun getClasses(@Header("AuthToken") token: String) : Call<List<Course>>

    @GET("/classes/folders")
    fun getFolders(@Header("AuthToken") token: String, @Query("class_id") classId: Int) : Call<List<Folder>>

    @POST("/classes/folders")
    fun postFolder(@Header("AuthToken") token: String, @Body folder: Folder) : Call<Void>

    @GET("/folders/questions")
    fun getQuestions(@Header("AuthToken") token: String, @Query("folder_id") folderId: Int) : Call<List<Question>>

    @POST("/folders/questions")
    fun postQuestion(@Header("AuthToken") token: String, @Body question: Question) : Call<Void>

    @POST("/questions")
    fun postAnswer(@Header("AuthToken") token: String, @Body studentAnswer: StudentAnswer) : Call<Void>

    @GET("/questions/answer")
    fun getAnswer(@Header("AuthToken") token: String, @Query("question_id") questionId: Int) : Call<StudentAnswer>

    @GET("/questions/rationale")
    fun getRationales(@Header("AuthToked") token: String, @Query("question_id") questionId: Int, @Query("class_id") classId: Int)

    companion object Factory {
        val ngrok = "be8a82db"
        val BASE_URL = "https://" + ngrok + ".ngrok.io"
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}