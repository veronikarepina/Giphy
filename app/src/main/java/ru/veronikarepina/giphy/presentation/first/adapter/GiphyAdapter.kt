package ru.veronikarepina.giphy.presentation.first.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.veronikarepina.giphy.R
import ru.veronikarepina.giphy.data.models.Data
import ru.veronikarepina.giphy.databinding.CardGiphyBinding
import ru.veronikarepina.giphy.presentation.first.Listener

class GiphyAdapter(
    private val listener: Listener
) : ListAdapter<Data, Holder>(Comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemBinding =
            CardGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(itemBinding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position), listener)
    }
}

private object Comparator : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}

class Holder(private val binding: CardGiphyBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Data, listener: Listener) {
        with(binding) {
            Glide.with(itemView).load(item.images.downsized_medium.url).into(giphyImage)
            itemView.setOnClickListener { listener.onClick(item) }
            favoriteIconOff.setOnClickListener {
                if (item.isFavorite) {
                    listener.deleteFavorite(item)
                } else {
                    listener.addFavorite(item)
                }
            }
            favoriteIconOff.setImageResource(
                if (item.isFavorite) {
                    R.drawable.favorite_painted
                } else {
                    R.drawable.favorite_icon
                }
            )
        }
    }
}

