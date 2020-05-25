package com.news.features.newsArticles.local

import androidx.lifecycle.LiveData
import com.news.features.newsArticles.model.Articles

/**
 * A repository of *articles*.
 *
 * This class retrieves data from local dao class [ArticlesDAO]
 *
 * @property articlesDAO the dao class to which interaction is to be made
 * @constructor Creates a repo.
 */
class ArticlesRepository(internal var articlesDAO: ArticlesDAO) {
    /**
     * Clears table for [articleslist] and
     * Adds [articleslist] to database.
     */
    suspend fun saveArticles(articleslist: List<Articles>) {
        articlesDAO.deleteArticlesList()
        articlesDAO.saveArticles(articleslist)
    }

    val articleslist: LiveData<List<Articles>> = articlesDAO.getArticlesList()

}