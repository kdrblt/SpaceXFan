package com.kadirbulut.spacexfan.ui.upcominglaunchesdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kadirbulut.spacexfan.databinding.ItemLaunchDetailBinding

class UpcomingLaunchDetailInfoAdapter :
    RecyclerView.Adapter<UpcomingLaunchDetailInfoAdapter.ViewHolder>() {

    private var dataSet = arrayListOf<LaunchDetailItem>()

    inner class ViewHolder(private var binding: ItemLaunchDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(launchDetailItem: LaunchDetailItem) {
            binding.let {
                it.item = launchDetailItem
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemLaunchDetailBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }

    // set dataset
    fun setList(data: List<LaunchDetailItem>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }
}
