package com.news.features.newsArticles.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news.R
import com.news.features.newsArticles.model.Articles
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_item_layout.view.*
import java.util.*

class MainActivityAdapter internal constructor(private val mListener: RecyclerViewClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mDataset = ArrayList<Articles>()

    fun updateData(dataset: List<Articles>) {
        mDataset.clear()
        mDataset.addAll(dataset)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val v = LayoutInflater.from(context).inflate(R.layout.main_item_layout, parent, false)
        return RowViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RowViewHolder) {
            holder.bind(position, mListener)
        }
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }


    inner class RowViewHolder(val itemview: View, val mListener: RecyclerViewClickListener) : RecyclerView.ViewHolder(itemview), View.OnClickListener {
        init {
            itemview.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            mListener.onClick(view, adapterPosition)
        }

        fun bind(position: Int, mListener: RecyclerViewClickListener) {
            val article: Articles = mDataset.get(position)
            itemview.tvtitle.text = article.title
            itemview.tvsourcename.text = article.source.name
            itemview.tvdescription.text = article.description
            Picasso.get().load(article.urlToImage).into(itemview.tvmedia);
            itemView.setOnClickListener { v -> mListener.onClick(v, position) }
        }
    }

}