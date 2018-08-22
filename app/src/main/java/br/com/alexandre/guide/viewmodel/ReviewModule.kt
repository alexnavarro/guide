package br.com.alexandre.guide.viewmodel

import br.com.alexandre.guide.review.respository.ReviewRespository
import br.com.alexandre.guide.review.respository.ReviewService
import dagger.Module
import dagger.Provides
import android.app.Application
import android.content.Context


@Module
class ReviewModule{

    @Provides
    internal fun provideReviewRepository(reviewService: ReviewService): ReviewRespository {
        return ReviewRespository(reviewService)
    }

    @Provides
    fun provideReviewModelFactory(repository: ReviewRespository, context: Context): ReviewViewModelFactory {
        return ReviewViewModelFactory(context as Application, repository)
    }
}