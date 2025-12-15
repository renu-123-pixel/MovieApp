package com.examplefourthjuly.movieapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.examplefourthjuly.movieapp.Activity.DetailActivity;
import com.examplefourthjuly.movieapp.Domain.DetailFilm;
import com.examplefourthjuly.movieapp.Domain.Show;
import com.examplefourthjuly.movieapp.R;

import java.util.List;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.ViewHolder> {
    private List<DetailFilm> items;
    Context context;

    public FilmListAdapter(List<DetailFilm> items) {
        setHasStableIds(true);
        this.items = items;
    }


    @NonNull
    @Override
    public FilmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_film,parent,false);
        context=parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DetailFilm item = items.get(position);

        holder.titleTxt.setText(item.getShow().getName());
        holder.ScoreTxt.setText(String.valueOf(item.getScore()));


        if (item.getShow() != null && item.getShow().getImage() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(item.getShow().getImage().getMedium())
                    .into(holder.pic);
        } if (item.getShow() != null && item.getShow().getImage() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(item.getShow().getImage().getMedium())
                    .into(holder.pic);
      }
//        else {
//            holder.pic.setImageResource(R.drawable.placeholder);
//        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        Intent i = new Intent(holder.itemView.getContext(), DetailActivity.class);
        i.putExtra("id", item.getShow().getId());
        holder.itemView.getContext().startActivity(i);
        }
       });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
    @Override
    public long getItemId(int position) {
        return items.get(position).getShow().getId();
    }
    public void updateData(List<DetailFilm> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt,ScoreTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.TitleTxt);
            ScoreTxt=itemView.findViewById(R.id.scoreTxt);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
