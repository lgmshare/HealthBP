package com.obody.health.feature

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import com.obody.health.BP_LEVEL_TEXT_1
import com.obody.health.BP_LEVEL_TEXT_2
import com.obody.health.BP_LEVEL_TEXT_3
import com.obody.health.BP_LEVEL_TEXT_4
import com.obody.health.BP_LEVEL_TEXT_5
import com.obody.health.BP_LEVEL_TEXT_6
import com.obody.health.DEFAULT_Diastolic
import com.obody.health.DEFAULT_Pulse
import com.obody.health.DEFAULT_Systolic
import com.obody.health.R
import com.obody.health.bean.BloodProfile
import com.obody.health.databases.AppDatabase
import com.obody.health.databinding.AddActivityBinding
import com.obody.health.utils.Utils
import kotlinx.coroutines.launch

class AddActivity : BaseActivity() {


    private lateinit var bloodProfile: BloodProfile

    private lateinit var binding: AddActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val lastData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("profile_data", BloodProfile::class.java)
        } else {
            intent.getParcelableExtra("profile_data")
        }
        if (lastData != null) {
            binding.touchOutside.setText("Reedit")
            bloodProfile = lastData
        } else {
            binding.touchOutside.setText("Enter Your BP")
            bloodProfile = BloodProfile()
            bloodProfile.systolic = DEFAULT_Systolic
            bloodProfile.diastolic = DEFAULT_Diastolic
            bloodProfile.pulse = DEFAULT_Pulse
        }

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnConfirm.setOnClickListener {
            clickConfirm()
        }

        val list = Array<String>(201) { "" }
        for (i in 0..200) {
            list[i] = i.toString()
        }
        binding.pickerSystolic.displayedValues = list
        binding.pickerSystolic.minValue = 0
        binding.pickerSystolic.maxValue = 200
        binding.pickerSystolic.value = bloodProfile.systolic
        binding.pickerSystolic.setOnValueChangedListener { picker, oldVal, newVal ->
            bloodProfile.systolic = picker.value
            updateLevelView()
        }
        binding.pickerDiastolic.displayedValues = list
        binding.pickerDiastolic.minValue = 0
        binding.pickerDiastolic.maxValue = 200
        binding.pickerDiastolic.value = bloodProfile.diastolic
        binding.pickerDiastolic.setOnValueChangedListener { picker, oldVal, newVal ->
            bloodProfile.diastolic = newVal
            updateLevelView()
        }
        binding.pickerPulse.displayedValues = list
        binding.pickerPulse.minValue = 0
        binding.pickerPulse.maxValue = 200
        binding.pickerPulse.value = bloodProfile.pulse
        binding.pickerPulse.setOnValueChangedListener { picker, oldVal, newVal ->
            bloodProfile.pulse = newVal
            updateLevelView()
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

    private fun clickConfirm() {
        val systolic = binding.pickerSystolic.value
        val diastolic = binding.pickerDiastolic.value
        val pulse = binding.pickerPulse.value

        bloodProfile.systolic = systolic
        bloodProfile.diastolic = diastolic
        bloodProfile.pulse = pulse

        if (systolic <= diastolic) {
            Utils.toast(this@AddActivity, "Please verify that the systolic reading exceeds the diastolic reading.")
            return
        }

        lifecycleScope.launch {
            kotlin.runCatching {
                if (bloodProfile.id > 0) {
                    AppDatabase.getInstance().bloodDao().update(bloodProfile)
                } else {
                    bloodProfile.datetime = System.currentTimeMillis()
                    AppDatabase.getInstance().bloodDao().insert(bloodProfile)
                }
            }.onSuccess {
                startActivity(Intent(this@AddActivity, RecordActivity::class.java).apply {
                    putExtra("profile_data", bloodProfile)
                })
                finish()
            }
        }
    }
}