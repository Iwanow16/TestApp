package ru.ivanov23.search.presentation.adapters.offer

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.ivanov23.base.adapter.base.AdapterDelegate
import ru.ivanov23.base.adapter.base.DelegateItem
import ru.ivanov23.base.databinding.ItemOfferBinding
import ru.ivanov23.ui_kit.models.OfferUi

class OfferDelegate : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemOfferBinding.inflate(
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
        (holder as ViewHolder).bind(item.content() as OfferUi)
    }

    override fun isOfViewType(item: DelegateItem): Boolean {
        return item is OfferDelegateItem
    }

    class ViewHolder(private val binding: ItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: OfferUi) {
            with(binding) {
                setupUi(model)
                root.setOnClickListener {
                    val context = binding.root.context
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(model.link)
                    }
                    context.startActivity(intent)
                }
            }
        }

        private fun ItemOfferBinding.setupUi(model: OfferUi) {
            model.button?.let {
                title.maxLines = 2
                button.text = model.button
                button.isVisible = true
            }
            model.iconId?.let {
                offerIcon.isVisible = true
                when (model.iconId) {
                    "near_vacancies" -> offerIcon.setImageResource(ru.ivanov23.ui_kit.R.drawable.ic_location)
                    "level_up_resume" -> offerIcon.setImageResource(ru.ivanov23.ui_kit.R.drawable.ic_star)
                    "temporary_job" -> offerIcon.setImageResource(ru.ivanov23.ui_kit.R.drawable.ic_document)
                }
            }
            title.text = model.title
        }
    }
}