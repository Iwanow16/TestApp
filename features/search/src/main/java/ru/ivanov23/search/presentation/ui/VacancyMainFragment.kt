package ru.ivanov23.search.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import ru.ivanov23.base.adapter.util.ItemOffsetDecoration
import ru.ivanov23.search.R
import ru.ivanov23.search.databinding.FragmentVacancyBinding
import ru.ivanov23.search.di.SearchComponentViewModel
import ru.ivanov23.search.mapper.toDelegateItem
import ru.ivanov23.search.presentation.adapters.MainAdapter
import ru.ivanov23.search.presentation.adapters.offer.OfferDelegate
import ru.ivanov23.search.presentation.adapters.vacancy.VacancyDelegate
import ru.ivanov23.search.presentation.viewmodel.SearchModelFactory
import ru.ivanov23.search.presentation.viewmodel.SearchViewModel
import ru.ivanov23.search.presentation.viewmodel.VacancyState
import javax.inject.Inject


class VacancyMainFragment : Fragment(R.layout.fragment_vacancy) {

    @Inject
    lateinit var factory: SearchModelFactory

    private val binding by viewBinding(FragmentVacancyBinding::bind)
    private val viewModel: SearchViewModel by viewModels { factory }

    private val offerAdapter: MainAdapter by lazy {
        MainAdapter()
    }

    private val vacancyAdapter: MainAdapter by lazy {
        MainAdapter()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<SearchComponentViewModel>()
            .newSearchComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        offerAdapter.apply {
            addDelegate(OfferDelegate())
        }

        vacancyAdapter.apply {
            addDelegate(VacancyDelegate())
        }

        binding.recommendationsList.adapter = offerAdapter
        binding.recommendationsList.addItemDecoration(ItemOffsetDecoration(16))

        binding.vacanciesList.adapter = vacancyAdapter
        binding.vacanciesList.addItemDecoration(ItemOffsetDecoration(16))

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is VacancyState.Error -> {
                        println(state.message)
                    }

                    VacancyState.Loading -> {
                        println("LOADING")
                    }

                    is VacancyState.Success -> {
                        println(state.data)
                        val offers = state.data.first
                        val vacancies = state.data.second
                        val vacancyCount = vacancies.count()
                        offerAdapter.submitList(offers.map { it.toDelegateItem() })
                        vacancyAdapter.submitList(vacancies
                            .take(3)
                            .map { it.toDelegateItem() }
                        )
                        binding.moreButton.text =
                            String.format(
                                getString(R.string.more_vacancies),
                                vacancies.count(),
                                requireContext().getVacancyCountText(vacancyCount)
                            )
                    }
                }
            }
        }
    }
}

fun Context.getVacancyCountText(count: Int): String {
    return when {
        count % 100 in 11..19 -> getString(R.string.vacancy_form_many)
        count % 10 == 1 -> getString(R.string.vacancy_form_one)
        count % 10 in 2..4 -> getString(R.string.vacancy_form_few)
        else -> getString(R.string.vacancy_form_many)
    }
}