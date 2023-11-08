package com.obody.health.feature

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.obody.health.databases.AppDatabase
import com.obody.health.databinding.RecordListActivityBinding
import com.obody.health.feature.adapter.RecordAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RecordListActivity : BaseActivity() {

    private lateinit var binding: RecordListActivityBinding

    private var loadJob: Job? = null

    private val adapter by lazy {
        RecordAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RecordListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        adapter.itemClickCallback = { item, position ->
            startActivity(Intent(this@RecordListActivity, AddActivity::class.java).apply {
                putExtra("profile_data", item)
            })
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    override fun onStop() {
        super.onStop()
        loadJob?.cancel()
    }

    private fun loadData() {
        loadJob = lifecycleScope.launch {
            kotlin.runCatching {
                val data = AppDatabase.getInstance().bloodDao().queryAll()
                val list = data.reversed()
                list
            }.onSuccess { data ->
                if (data.isNotEmpty()) {
                    adapter.dataList.clear()
                    adapter.dataList.addAll(data)
                    adapter.notifyDataSetChanged()
                }
            }.onFailure {

            }
        }
    }
}