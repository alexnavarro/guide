package br.com.alexandre.guide.di

import br.com.alexandre.guide.review.respository.ReviewService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(@Named("baseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
//                .client(okHttpClient)
                .build()
    }

    @Provides
    @Named("baseUrl")
    internal fun provideBaseUrl(): String {
        return "https://www.getyourguide.com/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/"
    }

    @Provides
    @Singleton
    internal fun provideReviewApi(retrofit: Retrofit): ReviewService {
        return retrofit.create<ReviewService>(ReviewService::class.java)
    }
}