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

    private val HEADER: Int = -1
    private val REVIEW: Int = 1
    private val LOADING: Int = -2

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
        val size = reviews.size

//        if (size > 0) {
//            if (hasHeader()) {
//                size++;
//            }
//        }

        return size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ReviewViewHolder){
            holder.bind(reviews[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)

        if (isLoading() && position == itemCount - 1) {
            return LOADING
        }

        return REVIEW
    }

    fun addAllReviews(reviews:List<Review>){
        this@ReviewAdapter.reviews.clear()
        this@ReviewAdapter.reviews.addAll(reviews)
        notifyDataSetChanged()
    }

    fun addAllReviewsByPage(reviews:List<Review>){
       this@ReviewAdapter.reviews.addAll(reviews)
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

        init {
            this.mainReviewItemBinding.root.setOnClickListener({ v ->
//                if (callback != null) {
//                    callback!!.onListingClicked(itemOfertaLancamentoBinding.getViewModel().getImovelBase())
//                }
            })
        }

        fun bind(review: Review) {
            mainReviewItemBinding.viewModel = review
//            if (itemOfertaLancamentoBinding.getViewModel() == null) {
//                itemOfertaLancamentoBinding.setViewModel(ListingViewModel(imovelBase, context))
//            } else {
//                itemOfertaLancamentoBinding.getViewModel().setImovelBase(imovelBase)
//            }

//            itemOfertaLancamentoBinding.ibFavorito.setOnClickListener(favoritar(context, imovelBase, itemOfertaLancamentoBinding.ibFavorito, screenSource, tracking))
        }
    }

    class LoadingViewHolder(context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_load, parent, false))
}