package com.news.features.newsArticles.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.news.features.newsArticles.model.Articles
import com.news.shared.utils.Constants
import com.news.shared.utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_details.*
import org.ocpsoft.prettytime.PrettyTime


class MainDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.news.R.layout.activity_main_details)

        setupUi()
        var artisledata: String = intent.getStringExtra(Constants.Utils.data);
        artisledata.let {
            var article: Articles = Gson().fromJson(artisledata, Articles::class.java)
            setData(article)
        }
    }

    private fun setupUi() {
        setSupportActionBar(topAppBar);
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()!!.setDisplayShowHomeEnabled(true);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() === android.R.id.home) {
            supportFinishAfterTransition();
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setData(article: Articles) {
        tvtitle.text = article.title
        tvsourcename.text = article.source.name
        tvdescription.text = article.description
        tvpublished.text = PrettyTime().format(article.publishedAt?.let { Utils.getDateFormat(it) });
        tvurl.text = article.url
        Picasso.get().load(article.urlToImage).into(tvmedia);

    }
}