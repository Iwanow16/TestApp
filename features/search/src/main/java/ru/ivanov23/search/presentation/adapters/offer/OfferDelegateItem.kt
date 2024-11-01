package ru.ivanov23.search.presentation.adapters.offer

import ru.ivanov23.base.adapter.DelegateItem

class OfferDelegateItem(
    val id: Int,
    private val value: OfferUi,
) : DelegateItem {
    override fun content(): Any = value

    override fun id(): Int = id

    override fun compareToOther(other: DelegateItem): Boolean {
        return (other as OfferDelegateItem).value == content()
    }
}