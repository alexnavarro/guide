package br.com.alexandre.guide

import br.com.alexandre.guide.di.DaggerAppComponent
import dagger.android.DaggerApplication

class GuideApp : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()
}