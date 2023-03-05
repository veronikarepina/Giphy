package ru.veronikarepina.giphy.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.veronikarepina.giphy.R
import ru.veronikarepina.giphy.data.models.Data
import ru.veronikarepina.giphy.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val bundleArgs: DetailFragmentArgs by navArgs()
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setDetail(bundleArgs.data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            favoriteOffDetail.setOnClickListener {
                viewModel.handleFavorite()
            }
            buttonBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            detailGiphy.observe(viewLifecycleOwner) {
                showInfo(it)
            }
        }
    }

    private fun showInfo(gif: Data) {
        with(binding) {
            context?.let { Glide.with(it).load(gif.images.downsized_large.url).into(gifImageDetail) }
            gifTitleDetail.text = gif.title
            favoriteOffDetail.setImageResource(
                if (gif.isFavorite) {
                    R.drawable.favorite_painted
                } else {
                    R.drawable.favorite_icon
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

