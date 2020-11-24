package domain

class Book private constructor(val title: String) {

    companion object {
        fun fromTitle(title: String): Book {
            return Book(title)
        }
    }
}