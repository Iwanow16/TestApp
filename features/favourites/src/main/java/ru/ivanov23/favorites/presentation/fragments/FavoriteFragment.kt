package ru.ivanov23.favorites.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import ru.ivanov23.base.adapter.MainAdapter
import ru.ivanov23.base.adapter.mapper.toDelegateItem
import ru.ivanov23.base.adapter.utils.getVacancyCountText
import ru.ivanov23.favorites.R
import ru.ivanov23.favorites.databinding.FragmentFavoriteBinding
import ru.ivanov23.favorites.di.FavouriteComponentViewModel
import ru.ivanov23.favorites.presentation.viewmodel.FavoriteModelFactory
import ru.ivanov23.favorites.presentation.viewmodel.FavoriteState
import ru.ivanov23.favorites.presentation.viewmodel.FavouriteViewModel
import ru.ivanov23.search.presentation.adapters.vacancy.VacancyDelegate
import ru.ivanov23.ui_kit.models.VacancyUi
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var factory: FavoriteModelFactory

    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavouriteViewModel by viewModels { factory }
    private val vacancyAdapter: MainAdapter by lazy { createVacancyAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeViewModel()
    }

    private fun injectDependencies() {
        ViewModelProvider(this)
            .get<FavouriteComponentViewModel>()
            .newFavouriteComponent
            .inject(this)
    }

    private fun createVacancyAdapter(): MainAdapter {
        return MainAdapter().apply {
            addDelegate(VacancyDelegate(
                onResponseClick = { },
                onFavoriteClick = { viewModel.toggleFavorite(it) },
                onItemClick = { }
            ))
        }
    }

    private fun setupUI() {
        binding.vacanciesList.adapter = vacancyAdapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is FavoriteState.Error -> handleError(state.message)
                    FavoriteState.Loading -> handleLoading()
                    is FavoriteState.Success -> handleSuccess(state.data)
                }
            }
        }
    }

    private fun handleError(message: String) {
        Log.d("FAVOURITE_FRAGMENT", message)
    }

    private fun handleLoading() {
        Log.d("FAVOURITE_FRAGMENT", "LOADING")
    }

    private fun handleSuccess(vacancies: List<VacancyUi>) {
        Log.d("FAVOURITE_FRAGMENT", "SUCCESS")

        vacancyAdapter.submitList(vacancies.map { it.toDelegateItem() })

        val vacancyCount = vacancies.count()
        binding.vacancyCount.text = String.format(
            getString(ru.ivanov23.ui_kit.R.string.vacancy_count),
            vacancyCount,
            requireContext().getVacancyCountText(vacancyCount)
        )
    }
}
