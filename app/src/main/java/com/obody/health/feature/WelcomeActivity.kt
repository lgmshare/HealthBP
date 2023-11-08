package com.obody.health.feature

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.obody.health.App
import com.obody.health.databinding.WelcomeActivityBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class WelcomeActivity : BaseActivity() {

    private var processJob: Job? = null

    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()

        processJob?.cancel()
        processJob = lifecycleScope.launch {
            var progress = 1
            while (isActive && progress < 100) {
                binding.progressbar.progress = progress
                delay(30)
                progress++
            }

            if (!App.getInstance().hot) {
                startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            }
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        processJob?.cancel()
    }
}