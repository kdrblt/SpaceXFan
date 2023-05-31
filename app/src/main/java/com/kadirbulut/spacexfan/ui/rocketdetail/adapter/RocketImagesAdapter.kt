package com.kadirbulut.spacexfan.ui.rocketdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kadirbulut.spacexfan.databinding.ItemRocketDetailSliderImageBinding

class RocketImagesAdapter : RecyclerView.Adapter<RocketImagesAdapter.ViewHolder>() {

    private var dataSet = arrayListOf<String>()

    inner class ViewHolder(private var binding: ItemRocketDetailSliderImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            binding.let {
                it.imageUrl = url
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRocketDetailSliderImageBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(
            dataSet[position]
        )
    }

    // Return the size of dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }

    // set dataset
    fun setList(data: List<String>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }
}
