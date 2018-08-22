package br.com.alexandre.guide

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.alexandre.guide.viewmodel.ReviewViewModel
import br.com.alexandre.guide.viewmodel.ReviewViewModelFactory
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ReviewViewModelFactory

    private var viewModel: ReviewViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReviewViewModel::class.java)
        viewModel?.loadReviews()

        viewModel?.reviews?.observe(this, Observer {
            it?.let {
                for ((index, element) in it.withIndex()){
                    Log.d("abacate", element.author)
                }
            }

            })
    }
}
