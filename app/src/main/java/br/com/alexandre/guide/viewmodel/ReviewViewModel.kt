package br.com.alexandre.guide.viewmodel

import android.arch.lifecycle.AndroidViewModel
import android.app.Application
import android.arch.lifecycle.MutableLiveData
import br.com.alexandre.guide.review.repository.*
import javax.inject.Inject


class ReviewViewModel @Inject
constructor(application: Application, private val repository: ReviewRepository) : AndroidViewModel(application){

    val reviews:MutableLiveData<ReviewResponse> = MutableLiveData()

    fun loadReviews() {
        val value = reviews.value
        if (value?.data == null || value.data.isEmpty()) {
            repository.loadReviews(reviews)
        }
    }

    fun loadReviews(page:Int, orderBy: OrderBy, direction: Direction) {
        repository.loadReviews(reviews, page, orderBy.description, direction.name)
    }
}