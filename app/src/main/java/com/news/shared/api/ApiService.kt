package com.news.shared.api

import com.news.features.newsArticles.model.ArticlesListResponse

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * List of server API calls
 */
interface ApiService {
    /**
     * GET all news
     */
    @GET("v2/top-headlines")
    fun getNews(@Query("country") country: String): Single<ArticlesListResponse>

}