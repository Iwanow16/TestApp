package ru.ivanov23.data.mapper

import ru.ivanov23.data.remote.models.AddressDto
import ru.ivanov23.data.remote.models.ExperienceDto
import ru.ivanov23.data.remote.models.OfferDto
import ru.ivanov23.data.remote.models.SalaryDto
import ru.ivanov23.data.remote.models.VacancyDto
import ru.ivanov23.domain.models.Address
import ru.ivanov23.domain.models.Experience
import ru.ivanov23.domain.models.Offer
import ru.ivanov23.domain.models.Salary
import ru.ivanov23.domain.models.Vacancy

fun OfferDto.toDomain(): Offer =
    Offer(
        button = this.button?.text,
        iconId = this.id,
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