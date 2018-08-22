package br.com.alexandre.guide

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

    private val reviews:MutableList<Review> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReviewViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.activity_main_review_item, parent, false))
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ReviewViewHolder).bind(reviews[position])
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

}