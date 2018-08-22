package br.com.alexandre.guide.review.repository

import com.google.gson.annotations.SerializedName

class ReviewResponse {

    @SerializedName("status")
    val isStatus: Boolean = false
    @SerializedName("total_reviews_comments")
    val totalReviewsComments: Int = 0
    @SerializedName("data")
    val data: List<Review>? = null
}