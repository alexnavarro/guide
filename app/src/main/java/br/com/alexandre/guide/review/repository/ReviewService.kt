package br.com.alexandre.guide.review.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewService {

    @GET("reviews.json?count=15")
    fun loadReviews(@Query("page") page: Int,
                    @Query("sortBy") sortBy: String,
                    @Query("direction") direction: String): Call<ReviewResponse>
}