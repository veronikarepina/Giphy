package ru.veronikarepina.giphy.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.veronikarepina.giphy.presentation.favorite.FavoriteFragment
import ru.veronikarepina.giphy.presentation.first.FirstFragment

class ViewPagerAdapter(fa: Fragment): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FirstFragment()
            else -> FavoriteFragment()
        }
    }
}