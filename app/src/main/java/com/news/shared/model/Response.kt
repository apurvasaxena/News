package com.news.shared.model

import com.news.shared.model.Status.*

class Response private constructor(val status: Status, val data: String?, val error: String?) {
    companion object {

        fun loading(): Response {
            return Response(LOADING, null, null)
        }

        fun success(): Response {
            return Response(SUCCESS, null, null)
        }

        fun error(error: String): Response {
            return Response(ERROR, null, error)

        }
    }
}
