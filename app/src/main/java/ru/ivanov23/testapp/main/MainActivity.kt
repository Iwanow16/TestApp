package ru.ivanov23.testapp.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import ru.ivanov23.base.adapter.mapper.toDelegateItem
import ru.ivanov23.base.adapter.utils.getVacancyCountText
import ru.ivanov23.favorites.presentation.fragments.FavoriteFragment
import ru.ivanov23.messages.MessagesFragment
import ru.ivanov23.profile.ProfileFragment
import ru.ivanov23.responses.ResponsesFragment
import ru.ivanov23.search.presentation.fragments.SearchFragment
import ru.ivanov23.testapp.R
import ru.ivanov23.testapp.appComponent
import ru.ivanov23.testapp.databinding.ActivityMainBinding
import ru.ivanov23.testapp.main.viewmodel.MainState
import ru.ivanov23.testapp.main.viewmodel.MainViewModel
import ru.ivanov23.testapp.main.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var factory: MainViewModelFactory

    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel: MainViewModel by viewModels { factory }

    private var currentTab: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent().inject(this)
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        setContentView(binding.root)

        setupBottomNavigation()
        setupBadge()
        observeViewModel()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_search -> {
                    selectTab("Search")
                    true
                }

                R.id.navigation_favourite -> {
                    selectTab("Favourite")
                    true
                }

                R.id.navigation_responses -> {
                    selectTab("Responses")
                    true
                }

                R.id.navigation_messages -> {
                    selectTab("Messages")
                    true
                }

                R.id.navigation_profile -> {
                    selectTab("Profile")
                    true
                }

                else -> false
            }
        }
    }

    private fun setupBadge() {
        binding.bottomNavigation.getOrCreateBadge(R.id.navigation_favourite)
            .apply {
                isVisible = false
                badgeTextColor = getColor(ru.ivanov23.ui_kit.R.color.white)
                backgroundColor = getColor(ru.ivanov23.ui_kit.R.color.red)
            }
    }

    private fun updateBadge(vacancyCount: Int) {
        binding.bottomNavigation.getOrCreateBadge(R.id.navigation_favourite)
            .apply {
                isVisible = vacancyCount > 0
                text = vacancyCount.toString()
            }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is MainState.Error -> handleError(state.message)
                    MainState.Loading -> handleLoading()
                    is MainState.Success -> handleSuccess(state.data)
                }
            }
        }
    }

    private fun handleError(message: String) {
        Log.d("MAIN_ACTIVITY", message)
    }

    private fun handleLoading() {
        Log.d("MAIN_ACTIVITY", "LOADING")
    }

    private fun handleSuccess(vacancyCount: Int) {
        Log.d("MAIN_ACTIVITY", "SUCCESS")
        updateBadge(vacancyCount)
    }

    private fun selectTab(tab: String) {
        if (currentTab == tab) return

        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(tab) ?: createFragment(tab)

        fragmentManager.beginTransaction().apply {
            currentTab?.let { currentTab ->
                fragmentManager.findFragmentByTag(currentTab)?.let { hide(it) }
            }
            if (!fragment.isAdded) {
                replace(R.id.main_container, fragment, tab)
            } else {
                show(fragment)
            }
            commit()
        }

        currentTab = tab
    }

    private fun createFragment(tab: String): Fragment {
        return when (tab) {
            "Search" -> SearchFragment()
            "Favourite" -> FavoriteFragment()
            "Responses" -> ResponsesFragment()
            "Messages" -> MessagesFragment()
            "Profile" -> ProfileFragment()
            else -> throw IllegalArgumentException("Unknown tab: $tab")
        }
    }
}
