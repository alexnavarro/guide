package br.com.alexandre.guide.review.repository

enum class OrderBy constructor(val description: String) {

    DATE("date_of_review"), RATING("rating")
}