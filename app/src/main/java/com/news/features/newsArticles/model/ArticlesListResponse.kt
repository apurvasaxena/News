package com.news.features.newsArticles.model

import com.google.gson.annotations.SerializedName

data class ArticlesListResponse(
        val articles: List<Articles>,
        val status: String,
        @SerializedName("totalResults")val totalResults: Int
)