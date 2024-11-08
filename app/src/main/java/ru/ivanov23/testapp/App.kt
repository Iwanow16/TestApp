package ru.ivanov23.testapp

import android.app.Application
import android.content.Context
import ru.ivanov23.favorites.di.FavoriteDepsStore
import ru.ivanov23.search.di.SearchDepsStore
import ru.ivanov23.testapp.di.AppComponent
import ru.ivanov23.testapp.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        SearchDepsStore.deps = appComponent
        FavoriteDepsStore.deps = appComponent
    }
}

fun Context.appComponent() = (applicationContext as App).appComponent