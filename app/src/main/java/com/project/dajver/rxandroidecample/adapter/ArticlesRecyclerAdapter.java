package com.project.dajver.rxandroidecample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.dajver.rxandroidecample.R;
import com.project.dajver.rxandroidecample.api.model.ArticlesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 11/14/17.
 */

public class ArticlesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ArticlesModel> newsModels = new ArrayList<>();
    private Context context;

    public ArticlesRecyclerAdapter(Context context, List<ArticlesModel> newsModels) {
        this.context = context;
        this.newsModels = newsModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aticle, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final NewsViewHolder viewHolder = (NewsViewHolder) holder;
        viewHolder.title.setText(newsModels.get(position).getName());
        Picasso.with(context).load(newsModels.get(position).getImageUrl()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView title;
        @BindView(R.id.image)
        public ImageView image;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}