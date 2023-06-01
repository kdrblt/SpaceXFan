package com.kadirbulut.spacexfan.ui.rocketdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kadirbulut.spacexfan.databinding.ItemRocketDetailBinding

class RocketDetailInfoAdapter : RecyclerView.Adapter<RocketDetailInfoAdapter.ViewHolder>() {

    private var dataSet = arrayListOf<RocketDetailItem>()

    inner class ViewHolder(private var binding: ItemRocketDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rocketDetailItem: RocketDetailItem) {
            binding.let {
                it.item = rocketDetailItem
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRocketDetailBinding.inflate(
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
    fun setList(data: List<RocketDetailItem>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }
}
