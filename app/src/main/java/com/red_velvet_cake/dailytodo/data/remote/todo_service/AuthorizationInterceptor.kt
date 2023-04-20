package com.red_velvet_cake.dailytodo.data.remote.todo_service

import com.red_velvet_cake.dailytodo.data.local.LocalDataImpl
import com.red_velvet_cake.dailytodo.data.remote.util.CustomException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    private val localDataImpl = LocalDataImpl()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = localDataImpl.getUserToken()
        val authHeaderValue = "$BEARER $token"
        val newRequest: Request = request.newBuilder()
            .addHeader(HEADER_AUTHORIZATION, authHeaderValue)
            .build()

        return chain.proceed(newRequest).also { response ->
            if (response.code == UNAUTHORIZED_STATUS_CODE) {
                throw CustomException.UnauthorizedUserException(response.message)
            }
        }
    }


    companion object {
        private const val UNAUTHORIZED_STATUS_CODE = 401
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val BEARER = "Bearer"
    }
}