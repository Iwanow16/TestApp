package ru.ivanov23.search.presentation.fragments

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
import ru.ivanov23.search.R
import ru.ivanov23.search.databinding.FragmentVacancyListBinding
import ru.ivanov23.search.di.SearchComponentViewModel
import ru.ivanov23.search.presentation.adapters.vacancy.VacancyDelegate
import ru.ivanov23.search.presentation.viewmodel.SearchModelFactory
import ru.ivanov23.search.presentation.viewmodel.SearchState
import ru.ivanov23.search.presentation.viewmodel.SearchViewModel
import ru.ivanov23.ui_kit.models.VacancyUi
import javax.inject.Inject

class VacancyListFragment : Fragment(R.layout.fragment_vacancy_list) {

    @Inject
    lateinit var factory: SearchModelFactory

    private val binding by viewBinding(FragmentVacancyListBinding::bind)
    private val viewModel: SearchViewModel by viewModels { factory }
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
            .get<SearchComponentViewModel>()
            .newSearchComponent
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
        binding.searchView.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.vacanciesList.adapter = vacancyAdapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is SearchState.Error -> handleError(state.message)
                    SearchState.Loading -> handleLoading()
                    is SearchState.Success -> handleSuccess(state.data.second)
                }
            }
        }
    }

    private fun handleError(message: String) {
        Log.d("VACANCY_LIST_FRAGMENT", message)
    }

    private fun handleLoading() {
        Log.d("VACANCY_LIST_FRAGMENT", "LOADING")
    }

    private fun handleSuccess(vacancies: List<VacancyUi>) {
        Log.d("VACANCY_LIST_FRAGMENT", "SUCCESS")

        vacancyAdapter.submitList(vacancies.map { it.toDelegateItem() })

        val vacancyCount = vacancies.count()
        binding.vacancyCount.text = String.format(
            getString(ru.ivanov23.ui_kit.R.string.vacancy_count),
            vacancyCount,
            requireContext().getVacancyCountText(vacancyCount)
        )
    }
}
