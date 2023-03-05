package ru.veronikarepina.giphy.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.veronikarepina.giphy.R
import ru.veronikarepina.giphy.data.models.Data
import ru.veronikarepina.giphy.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavoriteViewModel>()
    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter(
            onClickItemListener = { gif ->
                navigateToDetailFragment(gif)
            },
            deleteItemFavorite = { gif ->
                viewModel.deleteFavorite(gif)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRcFavorite()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            favoriteGiphy.observe(viewLifecycleOwner) { list ->
                binding.emptyImg.isVisible = list.isNullOrEmpty()
                adapter.submitList(list.asReversed())
            }
        }
    }

    private fun initRcFavorite() = with(binding) {
        recyclerFavorite.layoutManager = LinearLayoutManager(context)
        recyclerFavorite.adapter = adapter
    }

    private fun navigateToDetailFragment(item: Data) {
        val bundle = bundleOf("data" to item)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }
}

