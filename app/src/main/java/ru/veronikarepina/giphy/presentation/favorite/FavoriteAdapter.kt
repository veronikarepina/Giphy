package ru.veronikarepina.giphy.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.veronikarepina.giphy.R
import ru.veronikarepina.giphy.data.models.Data
import ru.veronikarepina.giphy.databinding.CardGiphyBinding

class FavoriteAdapter(
    private val onClickItemListener: (Data) -> Unit,
    private val deleteItemFavorite: (Data) -> Unit
) : ListAdapter<Data, HolderFavorite>(Comparator) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderFavorite {
        val itemBinding =
            CardGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HolderFavorite(itemBinding)
    }

    override fun onBindViewHolder(holder: HolderFavorite, position: Int) {
        holder.bind(getItem(position), onClickItemListener, deleteItemFavorite)
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

class HolderFavorite(private val binding: CardGiphyBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: Data,
        onClickItemListener: (Data) -> Unit,
        deleteItemFavorite: (Data) -> Unit
    ) {
        with(binding) {
            Glide.with(itemView).load(item.images.downsized_medium.url).into(giphyImage)
            favoriteIconOff.setImageResource(R.drawable.favorite_painted)
            favoriteIconOff.setOnClickListener { deleteItemFavorite.invoke(item) }
            itemView.setOnClickListener { onClickItemListener.invoke(item) }
        }
    }
}

