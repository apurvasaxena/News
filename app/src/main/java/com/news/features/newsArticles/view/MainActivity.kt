package com.news.features.newsArticles.view

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.news.R
import com.news.features.newsArticles.di.ArticlesDIModule
import com.news.features.newsArticles.di.DaggerArticlesComponent
import com.news.features.newsArticles.model.Articles
import com.news.features.newsArticles.viewModel.ArticlesViewModel
import com.news.features.newsArticles.viewModel.ArticlesViewModelFactory
import com.news.shared.model.Response
import com.news.shared.model.Status
import com.news.shared.utils.Constants
import com.news.shared.utils.NewsApplication
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.progress_bar_layout.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    private lateinit var mainactivityadapter: MainActivityAdapter
    var articleslist: List<Articles>? = null
    var context: Context? = null

    @Inject
    lateinit var viewModelFactory: ArticlesViewModelFactory
    lateinit var viewModel: ArticlesViewModel

    init {
        context = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
        initDependencies()
        setUpViewModel()
        viewModel.fetchArticlesFromLocalOrRemote()
    }

   /**
    * [RecyclerViewClickListener] interface method invokes
    * when item is clicked in [MainActivityAdapter]
    * */
    override fun onClick(view: View, position: Int) {
        var intent = Intent(this, MainDetailsActivity::class.java);
        intent.putExtra(Constants.Utils.data, Gson().toJson(articleslist?.get(position)))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    view,
                    getString(R.string.transition_name)
            )
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

    private fun setUpRecyclerView() {
        rvarticles.setLayoutManager(LinearLayoutManager(context))
        mainactivityadapter = MainActivityAdapter(this)
        rvarticles.setAdapter(mainactivityadapter)
    }

    private fun initDependencies() {
        DaggerArticlesComponent.builder()
                .netComponent((context!!.applicationContext as NewsApplication).netComponent)
                .articlesDIModule(ArticlesDIModule())
                .build().inject(this)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArticlesViewModel::class.java!!)
        displayProgressBar()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getapiresponse().observe(this, Observer { response ->
            processResponse(response)
        })

        viewModel.getarticlesresponse().observe(this, Observer { articlesResponse ->
            setArticlesData(articlesResponse)
        })
    }

    private fun processResponse(response: Response) {
        when (response.status) {
            Status.ERROR -> renderErrorState(response.error)
        }
    }

    private fun renderErrorState(message: String?) {
        hideProgressBar()
        Snackbar.make(rootview, message!!, Snackbar.LENGTH_LONG).show()
    }

    private fun displayProgressBar() {
        progressBarLayout.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        if (progressBarLayout.isVisible)
            progressBarLayout.visibility = View.GONE
    }

    private fun setArticlesData(articlesResponse: List<Articles>?) {
        articleslist = articlesResponse
        mainactivityadapter.updateData(articlesResponse!!)
        hideProgressBar()
    }
}
