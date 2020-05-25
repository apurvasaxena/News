package com.news.features.newsArticles.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.features.newsArticles.local.ArticlesRepository
import com.news.features.newsArticles.model.Articles
import com.news.features.newsArticles.model.ArticlesListResponse
import com.news.features.newsArticles.remote.ArticlesDataInterface
import com.news.shared.interfaces.DataInterface
import com.news.shared.model.Response
import com.news.shared.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Provides data for [com.news.features.newsArticles.view.MainActivity]
 */
class ArticlesViewModel(internal var data: ArticlesDataInterface, internal var articlesrepo: ArticlesRepository): ViewModel() {
    private val response = MutableLiveData<Response>()
    private val articleresponse = MutableLiveData<List<Articles>>()

    fun getapiresponse(): MutableLiveData<Response> {
        return response
    }

    fun getarticlesresponse(): LiveData<List<Articles>> {
        return articleresponse
    }

    /**
     * Called from [com.news.features.newsArticles.view.MainActivity] to fetch news details from [com.news.features.newsArticles.local.ArticlesRepository]
     * and [com.news.features.newsArticles.remote.ArticlesDataInterface] only if articles list in local database is null
     */
    fun fetchArticlesFromLocalOrRemote(){
        articlesrepo.articleslist.observeForever {
            if(it.isNullOrEmpty()){
                getArticles()
            }
            articleresponse.postValue(it)
        }
    }

    /**
     * Called from [fetchArticlesFromLocalOrRemote] to fetch news details from [com.news.features.newsArticles.remote.ArticlesDataInterface]
     * @param country of the country
     */
    fun getArticles() {
        data.getNews(Constants.Utils.name, object: DataInterface.Callback<ArticlesListResponse> {
            override fun onResponse(responsedata: ArticlesListResponse) {
                if (responsedata.totalResults != 0) {
                    insert(responsedata.articles)
                }
            }

            override fun onFailureWithMessage(message: String) {
                response.postValue(Response.error(message))
            }

            override fun onFailure() {
                response.postValue(Response.error(Constants.ERROR.somethingwrong))
            }

            override fun onNoNetworkFailure() {
                response.postValue(Response.error(Constants.ERROR.networkerror))
            }
        })
    }

    /**
     * Called from [getArticles] to locally save news details to [com.news.features.newsArticles.local.ArticlesRepository]
     * @param articles list of articles
     */
    fun insert(articles: List<Articles>) = viewModelScope.launch(Dispatchers.IO) {
        articlesrepo.saveArticles(articles)
    }

}