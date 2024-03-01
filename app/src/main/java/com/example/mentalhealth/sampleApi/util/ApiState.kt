package com.example.mentalhealth.sampleApi.util

import com.example.mentalhealth.sampleApi.retrofit.Post

sealed class ApiState {
    class Success(val data : List<Post> ) : ApiState()

    class Failure(val msg:Throwable) : ApiState()

    object Loading:ApiState()

    object Empty : ApiState()
}