package ru.ivanov23.base.adapter.mapper

import ru.ivanov23.base.adapter.base.DelegateItem
import ru.ivanov23.search.presentation.adapters.offer.OfferDelegateItem
import ru.ivanov23.search.presentation.adapters.vacancy.VacancyDelegateItem
import ru.ivanov23.ui_kit.models.OfferUi
import ru.ivanov23.ui_kit.models.VacancyUi

fun OfferUi.toDelegateItem(): DelegateItem =
    OfferDelegateItem(
        id = link.hashCode(),
        value = this
    )

fun VacancyUi.toDelegateItem(): DelegateItem =
    VacancyDelegateItem(
        id = id.hashCode(),
        value = this
    )