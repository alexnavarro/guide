package br.com.alexandre.guide

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import br.com.alexandre.guide.viewmodel.ReviewViewModel
import br.com.alexandre.guide.viewmodel.ReviewViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ReviewViewModelFactory

    private var viewModel: ReviewViewModel? = null
    private var adapter:ReviewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ReviewAdapter()
        recycler_reviews.adapter = adapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReviewViewModel::class.java)
        viewModel?.loadReviews()

        viewModel?.reviews?.observe(this, Observer {
            it?.let {
                (recycler_reviews.adapter as ReviewAdapter).addAllReviews(it)
            }
        })

        recycler_reviews.addOnScrollListener(object: RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

        })
    }
}