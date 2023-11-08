package com.obody.health.feature

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.obody.health.ARTICLE_TITLE_1
import com.obody.health.ARTICLE_TITLE_2
import com.obody.health.ARTICLE_TITLE_3
import com.obody.health.ARTICLE_TITLE_4
import com.obody.health.BP_LEVEL_TEXT_1
import com.obody.health.BP_LEVEL_TEXT_2
import com.obody.health.BP_LEVEL_TEXT_3
import com.obody.health.BP_LEVEL_TEXT_4
import com.obody.health.BP_LEVEL_TEXT_5
import com.obody.health.BP_LEVEL_TEXT_6
import com.obody.health.R
import com.obody.health.bean.BloodProfile
import com.obody.health.databinding.RecordActivityBinding
import com.obody.health.utils.Utils
import java.text.SimpleDateFormat

class RecordActivity : BaseActivity() {

    private lateinit var bloodProfile: BloodProfile

    private lateinit var binding: RecordActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecordActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val lastData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("profile_data", BloodProfile::class.java)
        } else {
            intent.getParcelableExtra("profile_data")
        }


        if (lastData == null) {
            finish()
            return
        }

        bloodProfile = lastData

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.tvDatetime.text = SimpleDateFormat("yyyy-MM-dd hh:mm").format(bloodProfile.datetime)
        binding.tvSystolic.text = bloodProfile.systolic.toString()
        binding.tvDiastolic.text = bloodProfile.diastolic.toString()
        binding.tvPulse.text = bloodProfile.pulse.toString()


        initArticle()

        binding.btnConfirm.setOnClickListener {
            startActivity(Intent(this@RecordActivity, MainActivity::class.java))
            finish()
        }

        binding.root.postDelayed({
            updateLevelView()
        }, 300)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun initArticle() {
        val list = mutableListOf<Int>().apply {
            add(0)
            add(1)
            add(2)
            add(3)
        }

        val a1 = list.random()
        setArticle(a1, binding.ivArticle1, binding.tvArticle1)
        list.remove(a1)
        val a2 = list.random()
        setArticle(a2, binding.ivArticle2, binding.tvArticle2)

        binding.layoutArticle1.setOnClickListener {
            startActivity(Intent(this@RecordActivity, ArticleActivity::class.java).apply {
                putExtra("index", a1)
            })
        }

        binding.layoutArticle2.setOnClickListener {
            startActivity(Intent(this@RecordActivity, ArticleActivity::class.java).apply {
                putExtra("index", a2)
            })
        }
    }

    private fun setArticle(index: Int, bg: ImageView, tvTitle: TextView) {
        when (index) {
            0 -> {
                bg.setImageResource(R.mipmap.article_1)
                tvTitle.setText(ARTICLE_TITLE_1)
            }

            1 -> {
                bg.setImageResource(R.mipmap.article_2)
                tvTitle.setText(ARTICLE_TITLE_2)
            }

            2 -> {
                bg.setImageResource(R.mipmap.article_3)
                tvTitle.setText(ARTICLE_TITLE_3)
            }

            3 -> {
                bg.setImageResource(R.mipmap.article_4)
                tvTitle.setText(ARTICLE_TITLE_4)
            }
        }
    }

    private fun updateLevelView() {
        binding.tvBpNum.text = getString(R.string.sys_x_dia_y, bloodProfile.systolic, bloodProfile.diastolic)
        val width = binding.ivIntroBar.width
        if (width <= 0) {
            return
        }
        Utils.log("${bloodProfile.systolic} ${bloodProfile.diastolic}")
        val itemWidth = width / 5
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        when (bloodProfile.level()) {
            0 -> {
                binding.tvBpLevel.setText(R.string.bp_level_1)
                binding.tvBpTips.setText(BP_LEVEL_TEXT_1)
                layoutParams.setMargins(0, 0, 0, 0)
            }

            1 -> {
                binding.tvBpLevel.setText(R.string.bp_level_2)
                binding.tvBpTips.setText(BP_LEVEL_TEXT_2)
                layoutParams.setMargins(itemWidth, 0, 0, 0)
            }

            2 -> {
                binding.tvBpLevel.setText(R.string.bp_level_3)
                binding.tvBpTips.setText(BP_LEVEL_TEXT_3)
                layoutParams.setMargins(itemWidth * 2, 0, 0, 0)
            }

            3 -> {
                binding.tvBpLevel.setText(R.string.bp_level_4)
                binding.tvBpTips.setText(BP_LEVEL_TEXT_4)
                layoutParams.setMargins(itemWidth * 3, 0, 0, 0)
            }

            4 -> {
                binding.tvBpLevel.setText(R.string.bp_level_5)
                binding.tvBpTips.setText(BP_LEVEL_TEXT_5)
                layoutParams.setMargins(itemWidth * 4, 0, 0, 0)
            }

            5 -> {
                binding.tvBpLevel.setText(R.string.bp_level_6)
                binding.tvBpTips.setText(BP_LEVEL_TEXT_6)
                layoutParams.setMargins(width - binding.ivIntroAngle.width, 0, 0, 0)
            }
        }
        binding.ivIntroAngle.layoutParams = layoutParams
    }
}