package preparation.firstCodingPrep

import java.time.LocalDateTime

data class Book(
    val id: String,
    val title: String
)

data class User(
    val id: String,
    val booksInPossession: List<Book> = emptyList(),
    val borrowedBooks: List<Borrow> = emptyList()
)

data class Borrow(
    val bookId: Book,
    val borrowedDate: LocalDateTime,
    val validUntil: LocalDateTime
)

fun main() {
    val book = Book(
        id = "1",
        title = "Effective Kotlin"
    )
    val user = User(
        id = "user1",
        booksInPossession = listOf(book),
        borrowedBooks = listOf()
    )

    user.copy()
}