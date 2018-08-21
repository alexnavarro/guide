package br.com.alexandre.guide.review.respository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("total_reviews_comments")
    @Expose
    private int totalReviewsComments;
    @SerializedName("data")
    @Expose
    private List<Review> review;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotalReviewsComments() {
        return totalReviewsComments;
    }

    public void setTotalReviewsComments(int totalReviewsComments) {
        this.totalReviewsComments = totalReviewsComments;
    }

    public List<Review> getData() {
        return review;
    }

    public void setData(List<Review> review) {
        this.review = review;
    }
}
