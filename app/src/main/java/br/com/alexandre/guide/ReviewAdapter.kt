package br.com.alexandre.guide

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.alexandre.guide.databinding.ActivityMainReviewItemBinding
import br.com.alexandre.guide.review.repository.Review
import android.view.LayoutInflater
import android.databinding.DataBindingUtil

/**
 * Created by alexandrenavarro on 8/22/18.
 */

class ReviewAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val HEADER: Int = 1
    private val REVIEW: Int = 2
    private val LOADING: Int = -1

    private val reviews:MutableList<Review> = mutableListOf()
    private var isLoading: Boolean = false
    private var hasEnded: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            LOADING -> LoadingViewHolder(parent.context, parent)
            else -> ReviewViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.activity_main_review_item, parent, false))
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    fun getTotal() = reviews.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ReviewViewHolder){
            holder.bind(reviews[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (isLoading && position == itemCount -1) {
            return LOADING
        }

        return REVIEW
    }

    fun addAll(reviews:List<Review>?, page:Int){
        if(page == 0) {
            this@ReviewAdapter.reviews.clear()
        }

        if(reviews != null && !reviews.isEmpty()) {
            this@ReviewAdapter.reviews.addAll(reviews)
        }

        setLoading(false)
        notifyDataSetChanged()
    }

    fun isLoading(): Boolean {
        return isLoading
    }

    fun setLoading(isLoading: Boolean) {
        this.isLoading = isLoading
    }

    fun hasEnded(): Boolean {
        return hasEnded
    }

    fun setEnded(hasEnded: Boolean) {
        this.hasEnded = hasEnded
    }

    private class ReviewViewHolder(private val mainReviewItemBinding: ActivityMainReviewItemBinding) : RecyclerView.ViewHolder(mainReviewItemBinding.root) {

        fun bind(review: Review) {
            mainReviewItemBinding.viewModel = review
        }
    }

    class LoadingViewHolder(context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_load, parent, false))
}