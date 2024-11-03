package ru.ivanov23.favorites.mapper

import ru.ivanov23.base.adapter.base.DelegateItem
import ru.ivanov23.domain.models.Vacancy
import ru.ivanov23.ui_kit.models.VacancyUi

fun Vacancy.toUi(): VacancyUi =
    VacancyUi(
        id = this.id,
        lookingNumber = this.lookingNumber,
        title = this.title,
        town = this.address.town,
        street = this.address.street,
        house = this.address.house,
        company = this.company,
        experience = this.experience.previewText,
        publishedDate = this.publishedDate,
        isFavorite = this.isFavorite,
        salaryFull = this.salary.full,
        shortShort = this.salary.short,
        schedules = this.schedules,
        appliedNumber = this.appliedNumber,
        description = this.description,
        responsibilities = this.responsibilities,
        questions = this.questions
    )

//fun VacancyUi.toDelegateItem(): DelegateItem =
//    VacancyDelegateItem(
//        id = id.hashCode(),
//        value = this
//    )