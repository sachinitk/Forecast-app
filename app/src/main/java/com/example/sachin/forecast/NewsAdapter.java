package com.example.sachin.forecast;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>  {

    private Context mCtx;
    private List<News> newsList;
    private ClickListener clickListener = null;

    public NewsAdapter(Context mCtx, List<News> newsList) {
        this.mCtx = mCtx;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_products,null);
        return  new NewsViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        //super.onBindViewHolder(holder, position);
       final News news = newsList.get(position);

        Glide.with(mCtx)
                .load(news.getImage()).into(holder.imageView);

        holder.textViewTitle.setText(news.getTitle());
        holder.textViewShortDesc.setText(news.getDesc());

       // holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(news.getImage()));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

  public    class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;
      private RelativeLayout newsitem;
      private ClickListener clickListener;
        public NewsViewHolder(final View itemView) {
            super(itemView);


            final News news = newsList.get(getAdapterPosition());


            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            imageView = itemView.findViewById(R.id.imageView);
            newsitem = (RelativeLayout)itemView.findViewById(R.id.relativelayout);
            newsitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),""+news.getTitle(),Toast.LENGTH_SHORT).show();
                   if(clickListener  != null)
                    {
                 clickListener.itemClicked(v,getAdapterPosition());

                    }
                }
         });






        }

    }
   public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

   public interface ClickListener {
       void itemClicked(View view, int position);
   }

}
