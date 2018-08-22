package br.com.alexandre.guide.di

import br.com.alexandre.guide.MainActivity
import br.com.alexandre.guide.viewmodel.ReviewModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [ReviewModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}