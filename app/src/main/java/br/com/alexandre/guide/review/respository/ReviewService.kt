package br.com.alexandre.guide.review.respository

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewService {

    @GET("reviews.json")
    fun loadReviews(@Query("count") quantityByPage: Int,
                    @Query("page") page: Int,
                    @Query("sortBy") sortBy: String,
                    @Query("direction") direction: String): Call<Response<ReviewResponse>>
}