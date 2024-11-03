package ru.ivanov23.search.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import ru.ivanov23.search.R
import ru.ivanov23.search.di.SearchComponentViewModel
import ru.ivanov23.search.navigation.SearchNavigator

class SearchFragment : Fragment(R.layout.fragment_search), SearchNavigator {

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<SearchComponentViewModel>()
            .newSearchComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            showMainFragment()
        }
    }

    private fun showMainFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, VacancyMainFragment())
            .commit()
    }

    override fun navigateToVacancyList() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, VacancyListFragment())
            .addToBackStack(null)
            .commit()
    }
}