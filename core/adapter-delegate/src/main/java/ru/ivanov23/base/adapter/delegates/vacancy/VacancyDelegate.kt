package ru.ivanov23.search.presentation.adapters.vacancy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.ivanov23.base.adapter.base.AdapterDelegate
import ru.ivanov23.base.adapter.base.DelegateItem
import ru.ivanov23.base.databinding.ItemVacancyBinding
import ru.ivanov23.base.adapter.utils.convertDateToText
import ru.ivanov23.base.adapter.utils.getHumanCountText
import ru.ivanov23.ui_kit.models.VacancyUi

class VacancyDelegate(
    private val onResponseClick: (VacancyUi) -> Unit,
    private val onFavoriteClick: (vacancyId: String) -> Unit,
    private val onItemClick: (VacancyUi) -> Unit,
) : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemVacancyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onResponseClick,
            onFavoriteClick,
            onItemClick
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as ViewHolder).bind(item.content() as VacancyUi)
    }

    override fun isOfViewType(item: DelegateItem): Boolean {
        return item is VacancyDelegateItem
    }

    class ViewHolder(
        private val binding: ItemVacancyBinding,
        private val onResponseClick: (VacancyUi) -> Unit,
        private val onFavoriteClick: (vacancyId: String) -> Unit,
        private val onItemClick: (VacancyUi) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: VacancyUi) {
            with(binding) {
                setupUi(model)
                root.setOnClickListener { onItemClick }
                responseButton.setOnClickListener { onResponseClick }

                var isFavorite = model.isFavorite

                favoriteButton.setOnClickListener {
                    isFavorite = !isFavorite
                    onFavoriteClick(model.id)
                    favoriteButton.setImageResource(getIconFavorite(isFavorite))
                }
            }
        }

        private fun ItemVacancyBinding.setupUi(model: VacancyUi) {
            model.lookingNumber?.let {
                viewersCount.isVisible = true
                viewersCount.text = root.context.getString(
                    ru.ivanov23.ui_kit.R.string.viewers_count_text,
                    model.lookingNumber,
                    root.context.getHumanCountText(model.lookingNumber!!)
                )
            }
            positionTitle.text = model.title
            location.text = model.town
            companyName.text = model.company
            experience.text = model.experience
            publicationDate.text = root.context.convertDateToText(model.publishedDate)
            favoriteButton.setImageResource(getIconFavorite(model.isFavorite))
        }

        private fun getIconFavorite(isFavorite: Boolean): Int {
            return if (isFavorite)
                ru.ivanov23.ui_kit.R.drawable.ic_heart_filled
            else
                ru.ivanov23.ui_kit.R.drawable.ic_heart
        }
    }
}