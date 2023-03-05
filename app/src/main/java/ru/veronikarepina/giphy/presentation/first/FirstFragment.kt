package ru.veronikarepina.giphy.presentation.first

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.veronikarepina.giphy.R
import ru.veronikarepina.giphy.data.models.Data
import ru.veronikarepina.giphy.databinding.FragmentFirstBinding
import ru.veronikarepina.giphy.presentation.first.adapter.GiphyAdapter

class FirstFragment : Fragment() {

    companion object{
        fun newInstance() = FirstFragment()
    }

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val gifListener = object : Listener {
        override fun onClick(item: Data) {
            navigateToDetailGiphy(item)
        }

        override fun addFavorite(item: Data) {
            showToast("Добавлено в избранное!")
            viewModel.handleFavorites(item)
        }

        override fun deleteFavorite(item: Data) {
            showToast("Удалено из избранного!")
            viewModel.handleFavorites(item)
        }
    }
    private val adapter: GiphyAdapter by lazy { GiphyAdapter(gifListener) }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()

        binding.editText.addTextChangedListener {
            viewModel.getGiphyData(it.toString())
        }

        viewModel.giphy.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        viewModel.favoriteGiphy.observe(viewLifecycleOwner) {
            viewModel.handleAllGiphys(it)
        }
    }

    private fun initRcView() = with(binding) {
        recycleView.adapter = adapter
    }

    private fun navigateToDetailGiphy(item: Data) {
        val bundle = bundleOf("data" to item)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }

    private fun showToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

