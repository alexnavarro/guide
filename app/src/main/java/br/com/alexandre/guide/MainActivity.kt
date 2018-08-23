package br.com.alexandre.guide

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import br.com.alexandre.guide.review.repository.Direction
import br.com.alexandre.guide.review.repository.OrderBy
import br.com.alexandre.guide.viewmodel.ReviewViewModel
import br.com.alexandre.guide.viewmodel.ReviewViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.widget.ArrayAdapter


class MainActivity : DaggerAppCompatActivity() {

    private val SAVED_INSTANCE_PAGE_KEY = "page"

    @Inject
    lateinit var viewModelFactory: ReviewViewModelFactory

    private var viewModel: ReviewViewModel? = null
    private var adapter: ReviewAdapter? = null

    private var mPage = 0
    private var mDirection = Direction.DESC
    private var mOrderBy = OrderBy.DATE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ReviewAdapter()
        adapter?.setLoading(true)
        recycler_reviews.adapter = adapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReviewViewModel::class.java)
        viewModel?.loadReviews()

        viewModel?.reviews?.observe(this, Observer {
            val reviewAdapter = recycler_reviews.adapter as ReviewAdapter
            reviewAdapter.addAll(it?.data, mPage)
            reviewAdapter.setEnded(it?.data == null || it.data.isEmpty() ||
                    it.totalReviewsComments == reviewAdapter.getTotal())
        })

        recycler_reviews.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            private val visibleThreshold = 5

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager
                    val visibleItemCount = layoutManager?.childCount ?: 0
                    val totalItemCount = layoutManager?.itemCount ?: 0
                    val firstVisibleItem = (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                            ?: 0

                    val reviewAdapter = recyclerView.adapter as ReviewAdapter
                    if (isReachingTheBottom(firstVisibleItem, visibleItemCount, totalItemCount, reviewAdapter)) {
                        reviewAdapter.setLoading(true)
                        requestPagination()

                    }
                }
            }

            private fun isReachingTheBottom(firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int, reviewAdapter: ReviewAdapter): Boolean {

                return !reviewAdapter.hasEnded() && !reviewAdapter.isLoading() && totalItemCount > 0 && firstVisibleItem + visibleItemCount + visibleThreshold > totalItemCount
            }

        })

        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_PAGE_KEY)) {
            mPage = savedInstanceState.getInt(SAVED_INSTANCE_PAGE_KEY, 0)
        }

        val spinnerDateRate = ArrayAdapter.createFromResource(this,
                R.array.order_by_date_or_rating, android.R.layout.simple_spinner_item)

        val spinnerAscDesc = ArrayAdapter.createFromResource(this,
                R.array.order_by_asc_desc, android.R.layout.simple_spinner_item)

        spinnerDateRate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDateRate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_asc_desc.adapter = spinnerAscDesc
        spinner_order_by.adapter = spinnerDateRate

        spinner_asc_desc.setSelection(0)
        spinner_order_by.setSelection(0)

        spinner_order_by.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mOrderBy = when (position) {0 -> OrderBy.DATE
                    else -> OrderBy.RATING; }
                val reviewAdapter = recycler_reviews.adapter as ReviewAdapter
                mPage = -1
                reviewAdapter.setLoading(true)
                requestPagination()
            }

        }

        spinner_asc_desc.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mDirection = when (position) {0 -> Direction.DESC
                    else -> Direction.ASC
                }
                val reviewAdapter = recycler_reviews.adapter as ReviewAdapter
                mPage = -1
                reviewAdapter.setLoading(true)
                requestPagination()
            }

        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(SAVED_INSTANCE_PAGE_KEY, mPage)
    }

    private fun requestPagination() {
        mPage++
        viewModel?.loadReviews(page = mPage, orderBy = mOrderBy, direction = mDirection)
    }
}