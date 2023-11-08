package com.obody.health.feature

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.obody.health.App
import com.obody.health.R
import com.obody.health.databinding.MainActivityBinding
import com.obody.health.databinding.WelcomeActivityBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: MainActivityBinding

    private val mFragmentList: ArrayList<Fragment> = ArrayList(3)
    private var mCurrentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.getInstance().hot =  true
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val homeProductFragment = HomeFragment()
            val productFragment = ArticleFragment()
            val newFragment = PrivacyFragment()
            mFragmentList.add(homeProductFragment)
            mFragmentList.add(productFragment)
            mFragmentList.add(newFragment)

            //设置ViewPager的最大缓存页面
            contentViewPager.isUserInputEnabled = false
            contentViewPager.offscreenPageLimit = mFragmentList.size
            contentViewPager.adapter = object : FragmentStateAdapter(supportFragmentManager, lifecycle) {
                override fun createFragment(position: Int): Fragment {
                    return mFragmentList[position]
                }

                override fun getItemCount(): Int {
                    return mFragmentList.size
                }
            }
            contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    mCurrentPage = position
                    when (position) {
                        0 -> bottomNavigationView.selectedItemId = R.id.home
                        1 -> bottomNavigationView.selectedItemId = R.id.product
                        2 -> bottomNavigationView.selectedItemId = R.id.merchant
                        else -> {}
                    }
                }
            })

            bottomNavigationView.itemIconTintList = null
            bottomNavigationView.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home -> contentViewPager.setCurrentItem(0, false)
                    R.id.product -> contentViewPager.setCurrentItem(1, false)
                    R.id.merchant -> contentViewPager.setCurrentItem(2, false)
                    else -> {}
                }
                true
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

    override fun onDestroy() {
        super.onDestroy()
        App.getInstance().hot =  false
    }
}