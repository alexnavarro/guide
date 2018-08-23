package br.com.alexandre.guide.viewmodel

import br.com.alexandre.guide.review.repository.ReviewRepository
import br.com.alexandre.guide.review.repository.ReviewService
import dagger.Module
import dagger.Provides
import android.app.Application
import android.content.Context


@Module
class ReviewModule{

    @Provides
    internal fun provideReviewRepository(reviewService: ReviewService): ReviewRepository {
        return ReviewRepository(reviewService)
    }

    @Provides
    fun provideReviewModelFactory(repository: ReviewRepository, context: Context): ReviewViewModelFactory {
        return ReviewViewModelFactory(context as Application, repository)
    }
}