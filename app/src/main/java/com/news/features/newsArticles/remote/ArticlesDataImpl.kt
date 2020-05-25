package com.news.features.newsArticles.remote

import android.util.Log
import com.news.features.newsArticles.model.ArticlesListResponse
import com.news.shared.api.ApiFailure
import com.news.shared.api.ApiService
import com.news.shared.interfaces.DataInterface
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticlesDataImpl(internal var apiService: ApiService, internal var apiFailure: ApiFailure) : ArticlesDataInterface {
    //NewsDataInterface METHODS
    override fun getNews(country: String, callback: DataInterface.Callback<ArticlesListResponse>?) {
       apiService.getNews(country)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(object : SingleObserver<ArticlesListResponse> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onError(e: Throwable) {
                        apiFailure.onApiFailure(callback!!, e)
                    }

                    override fun onSuccess(articleslist: ArticlesListResponse) {
                        Log.e("on ", "onSuccess");
                        callback!!.onResponse(articleslist)
                    }
                })
    }
}