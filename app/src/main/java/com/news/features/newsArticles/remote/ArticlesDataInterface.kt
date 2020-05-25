package com.news.features.newsArticles.remote

import com.news.features.newsArticles.model.ArticlesListResponse
import com.news.shared.interfaces.DataInterface

/**
 * Provides data for [com.news.features.newsArticles.viewModel]
 */
interface ArticlesDataInterface : DataInterface {
    /**
     * Get News from Backend
     * @param country the country of which news we want
     * @param callback the callback to the calling class
     */
    fun getNews(country: String, callback: DataInterface.Callback<ArticlesListResponse>?)
}