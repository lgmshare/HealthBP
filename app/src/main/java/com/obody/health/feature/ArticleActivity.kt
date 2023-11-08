package com.obody.health.feature

import android.os.Bundle
import com.obody.health.ARTICLE_TITLE_1
import com.obody.health.ARTICLE_TITLE_2
import com.obody.health.ARTICLE_TITLE_3
import com.obody.health.ARTICLE_TITLE_4
import com.obody.health.R
import com.obody.health.databinding.ArticleActivityBinding
import com.obody.health.databinding.PrivacyActivityBinding
import com.obody.health.databinding.WelcomeActivityBinding

class ArticleActivity : BaseActivity() {

    private lateinit var binding: ArticleActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArticleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        val index = intent.getIntExtra("index", 0)
        when (index) {
            0 -> {
                binding.articleBg.setImageResource(R.mipmap.article_1)
                binding.articleTitle.setText(ARTICLE_TITLE_1)
                binding.articleContent.setText(R.string.article_text_1)
            }

            1 -> {
                binding.articleBg.setImageResource(R.mipmap.article_2)
                binding.articleTitle.setText(ARTICLE_TITLE_2)
                binding.articleContent.setText(R.string.article_text_2)
            }

            2 -> {
                binding.articleBg.setImageResource(R.mipmap.article_3)
                binding.articleTitle.setText(ARTICLE_TITLE_3)
                binding.articleContent.setText(R.string.article_text_3)
            }

            3 -> {
                binding.articleBg.setImageResource(R.mipmap.article_4)
                binding.articleTitle.setText(ARTICLE_TITLE_4)
                binding.articleContent.setText(R.string.article_text_4)
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}