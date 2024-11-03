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
import ru.ivanov23.base.adapter.utils.HorizontalSpaceItemDecoration
import ru.ivanov23.base.adapter.utils.getVacancyCountText
import ru.ivanov23.search.R
import ru.ivanov23.search.databinding.FragmentVacancyBinding
import ru.ivanov23.search.di.SearchComponentViewModel
import ru.ivanov23.search.navigation.SearchNavigator
import ru.ivanov23.search.presentation.adapters.offer.OfferDelegate
import ru.ivanov23.search.presentation.adapters.vacancy.VacancyDelegate
import ru.ivanov23.search.presentation.viewmodel.SearchModelFactory
import ru.ivanov23.search.presentation.viewmodel.SearchState
import ru.ivanov23.search.presentation.viewmodel.SearchViewModel
import ru.ivanov23.ui_kit.models.OfferUi
import ru.ivanov23.ui_kit.models.VacancyUi
import javax.inject.Inject

class VacancyMainFragment : Fragment(R.layout.fragment_vacancy) {

    @Inject
    lateinit var factory: SearchModelFactory

    private var navigator: SearchNavigator? = null

    private val binding by viewBinding(FragmentVacancyBinding::bind)
    private val viewModel: SearchViewModel by viewModels { factory }

    private val offerAdapter by lazy { createOfferAdapter() }
    private val vacancyAdapter by lazy { createVacancyAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectDependencies()
        setNavigator()
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
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

    private fun setNavigator() {
        navigator = parentFragment as? SearchNavigator
            ?: throw RuntimeException("Parent fragment must implement SearchNavigator")
    }

    private fun setupUI() {
        binding.moreButton.setOnClickListener {
            navigator?.navigateToVacancyList()
        }

        binding.recommendationsList.apply {
            adapter = offerAdapter
            addItemDecoration(
                HorizontalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.item_spacing))
            )
        }

        binding.vacanciesList.adapter = vacancyAdapter
    }

    private fun createOfferAdapter(): MainAdapter {
        return MainAdapter().apply {
            addDelegate(OfferDelegate())
        }
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

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is SearchState.Error -> handleError(state.message)
                    SearchState.Loading -> handleLoading()
                    is SearchState.Success -> handleSuccess(state.data)
                }
            }
        }
    }

    private fun handleError(message: String) {
        Log.d("VACANCY_MAIN_FRAGMENT", message)
    }

    private fun handleLoading() {
        Log.d("VACANCY_MAIN_FRAGMENT", "LOADING")
    }

    private fun handleSuccess(data: Pair<List<OfferUi>, List<VacancyUi>>) {
        Log.d("VACANCY_MAIN_FRAGMENT", "SUCCESS")

        val (offers, vacancies) = data
        val vacancyCount = vacancies.count()

        offerAdapter.submitList(offers.map { it.toDelegateItem() })
        vacancyAdapter.submitList(
            vacancies
                .take(3)
                .map { it.toDelegateItem() }
        )

        binding.moreButton.text = String.format(
            getString(ru.ivanov23.ui_kit.R.string.more_vacancies),
            vacancies.count(),
            requireContext().getVacancyCountText(vacancyCount)
        )
    }
}
