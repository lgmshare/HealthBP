package com.obody.health.feature

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.obody.health.databinding.HomeFragmentBinding
import com.obody.health.databinding.PrivacyFragmentBinding

class PrivacyFragment : Fragment() {

    private lateinit var binding: PrivacyFragmentBinding

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        binding = PrivacyFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPrivacy.setOnClickListener {
            startActivity(Intent(activity, PrivacyActivity::class.java))
        }
    }
}