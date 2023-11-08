package com.obody.health.feature

import android.os.Bundle
import com.obody.health.databinding.PrivacyActivityBinding
import com.obody.health.databinding.WelcomeActivityBinding

class PrivacyActivity : BaseActivity() {

    private lateinit var binding: PrivacyActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PrivacyActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
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