package com.valentino.questionbank.api

import android.util.Log
import com.valentino.questionbank.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiService {
    val apiService = ApiInterface.create()

    fun registerUser(user: User, completion: () -> Unit) {
        val call = apiService.registerUser(user)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                if (response != null) {
                    completion()
                }
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun loginUser(login: Login, completion: (Session) -> Unit) {
        val call = apiService.loginUser(login)
        call.enqueue(object : Callback<Session> {
            override fun onResponse(call: Call<Session>?, response: Response<Session>?) {
                if (response?.body() != null) {
                    completion(response.body()!!)
                }
            }
            override fun onFailure(call: Call<Session>?, t: Throwable?) {}
        })
    }

    fun logoutUser(completion: () -> Unit) {
        completion()
    }

    fun sessionIsValid(token: String, completion: (Boolean) -> Unit) {
        val call = apiService.tokenIsValid(token)
        call.enqueue(object : Callback<ValidResponse> {
            override fun onResponse(call: Call<ValidResponse>?, response: Response<ValidResponse>?) {
                if (response?.body() != null) {
                    Log.d("SessionIsValid", response.body().toString())
                    response.body()?.valid?.let { completion(it) }
                }
            }
            override fun onFailure(call: Call<ValidResponse>?, t: Throwable?) {}
        })
    }

    fun postCourse(token: String, course: Course, completion: () -> Unit) {
        val call = apiService.postClass(token, course)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                if (response != null) {
                    completion()
                }
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun joinCourse(token: String, course: Course, completion: () -> Unit) {
        val call = apiService.joinClass(token, course)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                if (response != null) {
                    completion()
                }
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun getCourses(token: String, completion: (List<Course>) -> Unit) {
        val call = apiService.getClasses(token)
        call.enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>?, response: Response<List<Course>>?) {
                if (response?.body() != null) {
                    completion(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<Course>>?, t: Throwable?) {}
        })
    }

    fun postFolder(token: String, folder: Folder, completion: () -> Unit) {
        val call = apiService.postFolder(token, folder)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                if (response != null) {
                    completion()
                }
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.d("Retrofit", "Failed")
            }
        })
    }

    fun getFolders(token: String, classId: Int, completion: (List<Folder>) -> Unit) {
        val call = apiService.getFolders(token, classId)
        call.enqueue(object : Callback<List<Folder>> {
            override fun onResponse(call: Call<List<Folder>>?, response: Response<List<Folder>>?) {
                if (response?.body() != null) {
                    completion(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<Folder>>?, t: Throwable?) {}
        })
    }

    fun postQuestion(token: String, question: Question, completion: () -> Unit) {
        val call = apiService.postQuestion(token, question)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                if (response != null) {
                    completion()
                }
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                Log.d("Retrofit", "Failed")
            }
        })
    }

    fun getQuestions(token: String, folderId: Int, completion: (List<Question>) -> Unit) {
        val call = apiService.getQuestions(token, folderId)
        call.enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>?, response: Response<List<Question>>?) {
                if (response?.body() != null) {
                    completion(response.body()!!)
                }
            }
            override fun onFailure(call: Call<List<Question>>?, t: Throwable?) {}
        })
    }

    fun getAnswer(token: String, questionId: Int, completion: (StudentAnswer) -> Unit) {
        val call = apiService.getAnswer(token, questionId)
        call.enqueue(object : Callback<StudentAnswer> {
            override fun onResponse(call: Call<StudentAnswer>?, response: Response<StudentAnswer>?) {
                if (response?.body() != null) {
                    completion(response.body()!!)
                }
            }
            override fun onFailure(call: Call<StudentAnswer>?, t: Throwable?) {}
        })
    }
}