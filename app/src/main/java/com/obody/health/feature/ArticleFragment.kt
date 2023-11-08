package com.obody.health.feature

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.obody.health.ARTICLE_TITLE_1
import com.obody.health.ARTICLE_TITLE_2
import com.obody.health.ARTICLE_TITLE_3
import com.obody.health.ARTICLE_TITLE_4
import com.obody.health.databinding.ArticleFragmentBinding
import com.obody.health.feature.adapter.ArticleAdapter

class ArticleFragment : Fragment() {

    private lateinit var binding: ArticleFragmentBinding

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        binding = ArticleFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf<String>().apply {
            add(ARTICLE_TITLE_1)
            add(ARTICLE_TITLE_2)
            add(ARTICLE_TITLE_3)
            add(ARTICLE_TITLE_4)
        }

        val adapter = ArticleAdapter()
        adapter.dataList.addAll(list)
        adapter.itemClickCallback = { position ->
            startActivity(Intent(requireActivity(), ArticleActivity::class.java).apply {
                putExtra("index", position)
            })
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
}