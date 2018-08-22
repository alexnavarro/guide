package br.com.alexandre.guide.di

import android.app.Application

import javax.inject.Singleton

import br.com.alexandre.guide.GuideApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    NetworkModule::class, AppModule::class, BuildersModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: GuideApp)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}