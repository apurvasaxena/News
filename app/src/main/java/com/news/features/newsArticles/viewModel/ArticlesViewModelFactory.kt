package com.news.features.newsArticles.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.features.newsArticles.local.ArticlesRepository
import com.news.features.newsArticles.remote.ArticlesDataInterface

class ArticlesViewModelFactory (private val data: ArticlesDataInterface,
                                private val articlesrepo: ArticlesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticlesViewModel::class.java)) {
            return ArticlesViewModel(data!!, articlesrepo!!) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
