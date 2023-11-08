package com.obody.health.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.obody.health.R
import com.obody.health.bean.BloodProfile
import com.obody.health.databinding.RecordItemBinding
import java.text.SimpleDateFormat

class RecordAdapter : RecyclerView.Adapter<RecordAdapter.ItemViewHolder>() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm")

    var dataList: ArrayList<BloodProfile> = arrayListOf()

    inner class ItemViewHolder : RecyclerView.ViewHolder {

        val binding: RecordItemBinding

        constructor(item: View) : super(item) {
            binding = RecordItemBinding.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.run {


            when (item.level()) {
                0 -> {
                    stageLevel.setImageResource(R.mipmap.hypo_0)
                    stageTitle.setText(R.string.bp_level_1)
                }

                1 -> {
                    stageLevel.setImageResource(R.mipmap.hypo_1)
                    stageTitle.setText(R.string.bp_level_2)
                }

                2 -> {
                    stageLevel.setImageResource(R.mipmap.hypo_2)
                    stageTitle.setText(R.string.bp_level_3)
                }

                3 -> {
                    stageLevel.setImageResource(R.mipmap.hypo_3)
                    stageTitle.setText(R.string.bp_level_4)
                }

                4 -> {
                    stageLevel.setImageResource(R.mipmap.hypo_4)
                    stageTitle.setText(R.string.bp_level_5)
                }

                5 -> {
                    stageLevel.setImageResource(R.mipmap.hypo_5)
                    stageTitle.setText(R.string.bp_level_6)
                }
            }

            tvSystolic.text = item.systolic.toString()
            tvDiastolic.text = item.diastolic.toString()
            tvPulse.text = item.pulse.toString()
            tvDatetime.text = dateFormat.format(item.datetime)

            root.setOnClickListener {
                itemClickCallback?.invoke(item, position)
            }
        }
    }

    var itemClickCallback: ((BloodProfile, Int) -> Unit)? = null

}