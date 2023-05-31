package com.kadirbulut.spacexfan.ui.upcominglaunches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.ItemUpcomingLaunchBinding
import com.kadirbulut.spacexfan.domain.dto.LaunchModelDto

class LaunchesAdapter : RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {

    private var dataSet = arrayListOf<LaunchModelDto>()
    var onLaunchClicked: (String) -> Unit = {}

    inner class ViewHolder(private var binding: ItemUpcomingLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(launchModelDto: LaunchModelDto, position: Int) {
            binding.let {
                it.launch = launchModelDto
                it.cardViewLaunch.setBackgroundColor(
                    it.cardViewLaunch.resources.getColor(getBackgroundColor(position))
                )
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUpcomingLaunchBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position], position)
    }

    // Return the size of your (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }

    // set dataset
    fun setList(data: List<LaunchModelDto>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }

    private fun getBackgroundColor(index: Int): Int {
        val colors = arrayListOf(
            R.color.bg_color_1,
            R.color.bg_color_2,
            R.color.bg_color_3,
            R.color.bg_color_4,
            R.color.bg_color_5
        )
        return colors[index % 5]
    }
}
