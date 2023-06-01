package com.kadirbulut.spacexfan.ui.favourites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.kadirbulut.spacexfan.R
import com.kadirbulut.spacexfan.databinding.ItemRocketBinding
import com.kadirbulut.spacexfan.domain.dto.RocketModelDto

class FavouritesAdapter : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    private var dataSet = arrayListOf<RocketModelDto>()
    var onRocketClicked: (String, Boolean) -> Unit = { rocketId: String, isFavourite: Boolean -> }
    var removeFavouriteClicked: (String) -> Unit = {}
    var allItemsRemoved: () -> Unit = {}

    inner class ViewHolder(private var binding: ItemRocketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rocketModelDto: RocketModelDto, position: Int) {
            binding.let {
                it.rocket = rocketModelDto
                it.showFavIcon = true
                it.clItemRocket.setBackgroundColor(
                    it.clItemRocket.resources.getColor(getBackgroundColor(position))
                )
                /*
                 * if user click item, observe activity to go detail page
                 */
                it.clItemRocket.setOnClickListener {
                    onRocketClicked(rocketModelDto.id.toString(), binding.favButton.isChecked)
                }
                it.favButton.isChecked = true
                /*
                 * Toggle listener for fav button
                 */
                it.favButton.setOnClickListener {
                    val on = (it as ToggleButton).isChecked
                    if (!on) {
                        /*
                         * remove from favourites and notify list
                         */
                        removeFavouriteClicked(rocketModelDto.id.toString())
                        notifyItemRemoved(position)
                        dataSet.remove(rocketModelDto)
                        /*
                         * if all favourites are removed, notify fragment to show empty design
                         */
                        if (position == 0) {
                            allItemsRemoved()
                        }
                    }
                }
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
