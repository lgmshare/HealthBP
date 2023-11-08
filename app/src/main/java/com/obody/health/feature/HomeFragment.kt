package com.obody.health.feature

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.obody.health.databases.AppDatabase
import com.obody.health.databinding.HomeFragmentBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            btnAdd.setOnClickListener {
                startActivity(Intent(requireActivity(), AddActivity::class.java))
            }
            btnAdd2.setOnClickListener {
                startActivity(Intent(requireActivity(), AddActivity::class.java))
            }
            btnHistory.setOnClickListener {
                startActivity(Intent(requireActivity(), RecordListActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        lifecycleScope.launch {
            kotlin.runCatching {
                AppDatabase.getInstance().bloodDao().queryAll()
            }.onSuccess { list ->
                if (list.isEmpty()) {
                    binding.noneLayout.isVisible = true
                    binding.recordLayout.isVisible = false
                } else {
                    binding.noneLayout.isVisible = false
                    binding.recordLayout.isVisible = true
                    val profile = list.last()
                    binding.tvSystolic.text = profile.systolic.toString()
                    binding.tvDiastolic.text = profile.diastolic.toString()
                    binding.tvPulse.text = profile.pulse.toString()
                }
            }
        }
    }
}