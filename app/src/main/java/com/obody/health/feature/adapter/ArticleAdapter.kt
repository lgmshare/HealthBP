package com.obody.health.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.obody.health.R

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ItemViewHolder>() {

    var dataList: ArrayList<String> = arrayListOf()

    inner class ItemViewHolder : RecyclerView.ViewHolder {

        val article_item: ConstraintLayout
        val article_bg: ImageView
        val article_title: TextView

        constructor(item: View) : super(item) {
            article_item = item.findViewById(R.id.article_item)
            article_bg = item.findViewById(R.id.article_bg)
            article_title = item.findViewById(R.id.article_title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataList[position]
        when (position) {
            0 -> {
                holder.article_bg.setImageResource(R.mipmap.article_1)
            }

            1 -> {
                holder.article_bg.setImageResource(R.mipmap.article_2)
            }

            2 -> {
                holder.article_bg.setImageResource(R.mipmap.article_3)
            }

            3 -> {
                holder.article_bg.setImageResource(R.mipmap.article_4)
            }
        }
        holder.article_title.text = item
        holder.itemView.setOnClickListener {
            itemClickCallback?.invoke(position)
        }
    }

    var itemClickCallback: ((Int) -> Unit)? = null

}