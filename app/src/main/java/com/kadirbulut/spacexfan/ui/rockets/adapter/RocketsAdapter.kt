package com.kadirbulut.spacexfan.ui.rockets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.ItemRocketBinding
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto

class RocketsAdapter : RecyclerView.Adapter<RocketsAdapter.ViewHolder>() {

    private var dataSet = arrayListOf<RocketModelDto>()
    var onRocketClicked: (String) -> Unit = {}

    inner class ViewHolder(private var binding: ItemRocketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rocketModelDto: RocketModelDto, position: Int) {
            binding.let {
                System.out.println("8937458934789573489 >>>>> " + getBackgroundColor(position))
                it.rocket = rocketModelDto
                it.clItemRocket.setBackgroundColor(
                    it.clItemRocket.resources.getColor(getBackgroundColor(position))
                )
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemRocketBinding.inflate(
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
    fun setList(data: List<RocketModelDto>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyItemRangeInserted(0, data.size)
    }

    private fun getBackgroundColor(index: Int): Int {
        val colors = arrayListOf(
            R.color.orange,
            R.color.light_orange,
            R.color.yellow,
            R.color.light_blue,
            R.color.blue
        )
        return colors[index % 5]
    }
}
