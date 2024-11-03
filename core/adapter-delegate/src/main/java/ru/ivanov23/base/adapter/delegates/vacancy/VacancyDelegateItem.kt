package ru.ivanov23.search.presentation.adapters.vacancy

import ru.ivanov23.base.adapter.base.DelegateItem
import ru.ivanov23.ui_kit.models.VacancyUi

class VacancyDelegateItem(
    val id: Int,
    private val value: VacancyUi,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as VacancyDelegateItem).value == content()
    }
}