package br.com.alexandre.guide.review.respository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ReviewRespository @Inject constructor(private val reviewService: ReviewService){

    @JvmOverloads
    fun loadReviews(liveData: MutableLiveData<List<Review>>, page: Int = 0, sortBy: String = OrderBy.DATE.description,
                    direction: String = Direction.DESC.name.toLowerCase()) {

        reviewService.loadReviews(page, sortBy, direction)
                .enqueue(object: Callback<ReviewResponse> {

                    override fun onFailure(call: Call<ReviewResponse>, t: Throwable) {
                        Log.d("Error", t.message)
                    }

                    override fun onResponse(call: Call<ReviewResponse>, response: Response<ReviewResponse>) {
                       liveData.value = response.body()?.data
                    }
                })
    }
}