package domain

import java.time.LocalDate

data class Person(
    val firstName: String = "",
    val lastName: String? = "",
    val gender: Gender = Gender.F,
    val birthday: LocalDate = LocalDate.now()
)