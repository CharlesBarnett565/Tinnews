package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;

import java.util.ArrayList;
import java.util.List;
import com.laioffer.tinnews.R;
import com.squareup.picasso.Picasso;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder>{//extend把java class变成了android class
    // 1. Supporting data:
    private List<Article> articles = new ArrayList<>();
    private ItemCallback itemCallback;

    public void setArticles(List<Article> newsList) {
        articles.clear();//清空以前的数据
        articles.addAll(newsList);//加入现有数据
        notifyDataSetChanged();//通知recycleview 数据更新，准备bind新数据;//因为这是recycle view的function,所以我们要extend RecycleView的class;

    }
    interface ItemCallback {
        void onOpenDetails(Article article);
    }


    public void setItemCallback(ItemCallback itemCallback) {
        this.itemCallback = itemCallback;
    }

    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {// create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {// bind data;
        Article article = articles.get(position);

        holder.itemTitleTextView.setText(article.title);
        if(article.favorite){
            holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        }else{
            holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_border_24dp);
        }

        holder.favoriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                article.favorite =!article.favorite;
                notifyDataSetChanged();
            }
        });
        if(article.urlToImage !=null){
            Picasso.get().load(article.urlToImage).into(holder.itemImageView);//bind时候显示picasso里面的图片;
        }

        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));

    }

    @Override
    public int getItemCount() {//recycle view里面最多有多少个;
        return articles.size();
    }

    // 2. Adapter overrides:
    // TODO

    // 3. SearchNewsViewHolder:
    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {

        ImageView favoriteImageView;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }
    }
}
