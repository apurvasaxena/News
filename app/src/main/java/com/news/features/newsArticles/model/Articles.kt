package com.news.features.newsArticles.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Articles(
        @PrimaryKey(autoGenerate = true)
        val id: Int? = null,
        val author: String? = null,
        val content: String? = null,
        val description: String? = null,
        @SerializedName("publishedAt") val publishedAt: String? = null,
        val source: Source,
        val title: String,
        val url: String,
        @SerializedName("urlToImage")val urlToImage: String? = null
)