package br.com.alexandre.guide.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.app.Application
import android.arch.lifecycle.MutableLiveData
import br.com.alexandre.guide.review.repository.Direction
import br.com.alexandre.guide.review.repository.OrderBy
import br.com.alexandre.guide.review.repository.Review
import br.com.alexandre.guide.review.repository.ReviewRespository
import javax.inject.Inject


class ReviewViewModel @Inject
constructor(application: Application, private val repository: ReviewRespository) : AndroidViewModel(application){

    val reviews:MutableLiveData<List<Review>> = MutableLiveData()

    fun loadReviews() {
        val value = reviews.value
        if (value == null || value.isEmpty()) {
            repository.loadReviews(reviews)
        }
    }

    fun loadReviews(page:Int, orderBy: OrderBy, direction: Direction) {
        val value = reviews.value
        if (value == null || value.isEmpty()) {
            repository.loadReviews(reviews, page, orderBy.description, direction.name)
        }
    }
}