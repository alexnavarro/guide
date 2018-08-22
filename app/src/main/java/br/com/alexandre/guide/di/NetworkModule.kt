package br.com.alexandre.guide.di

import br.com.alexandre.guide.BuildConfig
import br.com.alexandre.guide.review.repository.ReviewService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                            @Named("headerParameters") header: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(header)
                .build()
    }


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    @Named("headerParameters")
    fun provideHttpHeaderInterceptor(): Interceptor {

       return Interceptor { chain ->
           chain.proceed(chain.request().newBuilder()
                   .addHeader("Accept", "application/json")
                   .addHeader("Content-Type", "application/json")
                   .addHeader("User-Agent", BuildConfig.API_KEY)
                   .build())
       }
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