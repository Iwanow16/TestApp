package ru.ivanov23.search.presentation.adapters.vacancy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.ivanov23.base.adapter.AdapterDelegate
import ru.ivanov23.base.adapter.DelegateItem
import ru.ivanov23.search.R
import ru.ivanov23.search.databinding.ItemVacancyBinding
import java.text.SimpleDateFormat
import java.util.Locale

class VacancyDelegate : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemVacancyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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

    class ViewHolder(private val binding: ItemVacancyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: VacancyUi) {
            with(binding) {
                setupUi(model)
                root.setOnClickListener {

                }
            }
        }

        private fun ItemVacancyBinding.setupUi(model: VacancyUi) {
            model.lookingNumber?.let {
                viewersCount.isVisible = true
                viewersCount.text = root.context.getString(
                    R.string.viewers_count_text,
                    model.lookingNumber,
                    root.context.getHumanCountText(model.lookingNumber)
                )
            }
            positionTitle.text = model.title
            location.text = model.town
            companyName.text = model.company
            experience.text = model.experience
            publicationDate.text = root.context.convertDateToText(model.publishedDate)

            if (model.isFavorite)
                favoriteButton.setImageResource(ru.ivanov23.ui_kit.R.drawable.ic_heart_filled)
            else
                favoriteButton.setImageResource(ru.ivanov23.ui_kit.R.drawable.ic_heart)
        }
    }
}

fun Context.getHumanCountText(count: Int): String {
    return when {
        count % 100 in 11..19 -> getString(R.string.human_form_many)
        count % 10 == 1 -> getString(R.string.human_form_one)
        count % 10 in 2..4 -> getString(R.string.human_form_few)
        else -> getString(R.string.human_form_many)
    }
}

fun Context.convertDateToText(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM", Locale("ru", "RU"))
    val date = inputFormat.parse(dateString)
    return String.format(
        getString(R.string.published_date),
        outputFormat.format(date!!)
    )
}