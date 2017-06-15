package com.kshrd.articlecms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kshrd.articlecms.entity.ArticleResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pirang on 6/14/17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    List<ArticleResponse.Article> articleList;

    public ArticleAdapter() {
        articleList = new ArrayList<>();
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_custom, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

        ArticleResponse.Article article = articleList.get(position);

        holder.tvTitle.setText("Title : "+article.getTitle());
        holder.tvDescription.setText("Description : "+article.getDescription());
        holder.tvceatedate.setText("CreatedDate : "+article.getCreatedDate());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public void addMoreItems(List<ArticleResponse.Article> articleList){
        this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }
    public void clearItems(){
        this.articleList.removeAll( articleList);


    }


    /**
     * View Holder
     */
    class ArticleViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvDescription)
        TextView tvDescription;

        @BindView(R.id.txtcreatedate)
        TextView tvceatedate;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
