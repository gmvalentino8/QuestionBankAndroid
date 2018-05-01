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
                completion()
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun loginUser(login: Login, completion: (Session) -> Unit) {
        val call = apiService.loginUser(login)
        call.enqueue(object : Callback<Session> {
            override fun onResponse(call: Call<Session>?, response: Response<Session>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<Session>?, t: Throwable?) {}
        })
    }

    fun getUser(token: String, userId: String, completion: (User) -> Unit) {
        val call = apiService.getUser(token, userId)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<User>?, t: Throwable?) { }
        })
    }

    fun logoutUser(completion: () -> Unit) {
        completion()
    }

    fun sessionIsValid(token: String, completion: (Boolean) -> Unit) {
        val call = apiService.tokenIsValid(token)
        call.enqueue(object : Callback<ValidResponse> {
            override fun onResponse(call: Call<ValidResponse>?, response: Response<ValidResponse>?) {
                if (response != null) {
                    Log.d("Login", response.body().toString())
                    completion(response.body()?.valid!!)
                }
            }
            override fun onFailure(call: Call<ValidResponse>?, t: Throwable?) {}
        })
    }

    fun postCourse(token: String, course: Course, completion: () -> Unit) {
        val call = apiService.postClass(token, course)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                completion()
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun joinCourse(token: String, course: Course, completion: () -> Unit) {
        val call = apiService.joinClass(token, course)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                Log.d("Join Course", "On Response")
                completion()
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun getCourses(token: String, completion: (List<Course>) -> Unit) {
        val call = apiService.getClasses(token)
        call.enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>?, response: Response<List<Course>>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<List<Course>>?, t: Throwable?) {}
        })
    }

    fun filterCourses(token: String, schoolId: Int, name: String = "", code: String = "",
                      term: String = "", completion: (List<Course>) -> Unit) {
        val call = apiService.filterClasses(token, schoolId, name, code, term)
        call.enqueue(object : Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>?, response: Response<List<Course>>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<List<Course>>?, t: Throwable?) {}
        })
    }

    fun postFolder(token: String, folder: Folder, completion: () -> Unit) {
        val call = apiService.postFolder(token, folder)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                completion()
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun getFolders(token: String, classId: Int, completion: (List<Folder>) -> Unit) {
        val call = apiService.getFolders(token, classId)
        call.enqueue(object : Callback<List<Folder>> {
            override fun onResponse(call: Call<List<Folder>>?, response: Response<List<Folder>>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<List<Folder>>?, t: Throwable?) {}
        })
    }

    fun postQuestion(token: String, question: Question, completion: () -> Unit) {
        val call = apiService.postQuestion(token, question)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                completion()
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun getQuestions(token: String, folderId: Int, completion: (List<Question>) -> Unit) {
        val call = apiService.getQuestions(token, folderId)
        call.enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>?, response: Response<List<Question>>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<List<Question>>?, t: Throwable?) {}
        })
    }

    fun filterQuestions(token: String, name: String, tag: String, completion: (List<Question>) -> Unit) {
        val call = apiService.filterQuestions(token, name, tag)
        call.enqueue(object : Callback<List<Question>> {
            override fun onResponse(call: Call<List<Question>>?, response: Response<List<Question>>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<List<Question>>?, t: Throwable?) {}
        })
    }

    fun postAnswer(token: String, answer: StudentAnswer, completion: () -> Unit) {
        val call = apiService.postAnswer(token, answer)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                completion()
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {}
        })
    }

    fun getAnswer(token: String, questionId: Int, completion: (StudentAnswer) -> Unit) {
        val call = apiService.getAnswer(token, questionId)
        call.enqueue(object : Callback<StudentAnswer> {
            override fun onResponse(call: Call<StudentAnswer>?, response: Response<StudentAnswer>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<StudentAnswer>?, t: Throwable?) {}
        })
    }

    fun getRationales(token: String, questionId: Int, classId: Int, completion: (List<Rationale>) -> Unit) {
        val call = apiService.getRationales(token, questionId, classId)
        call.enqueue(object : Callback<List<Rationale>> {
            override fun onResponse(call: Call<List<Rationale>>?, response: Response<List<Rationale>>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<List<Rationale>>?, t: Throwable?) {}
        })
    }

    fun getAnalysis(token: String, questionId: Int, completion: (Analysis) -> Unit) {
        val call = apiService.getAnalysis(token, questionId)
        call.enqueue(object : Callback<Analysis> {
            override fun onResponse(call: Call<Analysis>?, response: Response<Analysis>?) {
                response?.body()?.let(completion)
            }
            override fun onFailure(call: Call<Analysis>?, t: Throwable?) {}
        })
    }
}