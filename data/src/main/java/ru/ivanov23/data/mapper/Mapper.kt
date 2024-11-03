package ru.ivanov23.data.mapper

import ru.ivanov23.data.source.local.entity.OfferEntity
import ru.ivanov23.data.source.local.entity.VacancyEntity
import ru.ivanov23.data.source.remote.models.AddressDto
import ru.ivanov23.data.source.remote.models.ExperienceDto
import ru.ivanov23.data.source.remote.models.OfferDto
import ru.ivanov23.data.source.remote.models.SalaryDto
import ru.ivanov23.data.source.remote.models.VacancyDto
import ru.ivanov23.domain.models.Address
import ru.ivanov23.domain.models.Experience
import ru.ivanov23.domain.models.Offer
import ru.ivanov23.domain.models.Salary
import ru.ivanov23.domain.models.Vacancy

fun OfferDto.toDomain(): Offer =
    Offer(
        button = this.button?.text,
        iconId = this.iconId,
        link = this.link,
        title = this.title
    )

fun VacancyDto.toDomain(): Vacancy =
    Vacancy(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = address.toAddress(),
        company = company,
        experience = experience.toExperience(),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = salary.toSalary(),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )

fun AddressDto.toAddress() = Address(
    town = town,
    street = street,
    house = house
)

fun ExperienceDto.toExperience() = Experience(
    previewText = previewText,
    text = text
)

fun SalaryDto.toSalary() = Salary(
    full = full,
    short = short
)

fun OfferDto.toEntity(): OfferEntity =
    OfferEntity(
        id = title.hashCode(),
        iconId = iconId,
        button = button?.text,
        link = link,
        title = title
    )

fun OfferEntity.toDomain(): Offer =
    Offer(
        button = button,
        iconId = iconId,
        link = link,
        title = title
    )

fun VacancyDto.toEntity(): VacancyEntity =
    VacancyEntity(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        town = address.town,
        street = address.street,
        house = address.house,
        company = company,
        experiencePreview = experience.previewText,
        experienceText = experience.text,
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salaryFull = salary.full,
        salaryShort = salary.short,
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )

fun VacancyEntity.toDomain(): Vacancy =
    Vacancy(
        id = id,
        lookingNumber = lookingNumber,
        title = title,
        address = Address(town, street, house),
        company = company,
        experience = Experience(experiencePreview, experienceText),
        publishedDate = publishedDate,
        isFavorite = isFavorite,
        salary = Salary(salaryFull, salaryShort),
        schedules = schedules,
        appliedNumber = appliedNumber,
        description = description,
        responsibilities = responsibilities,
        questions = questions
    )