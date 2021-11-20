package com.example.utspam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utspam.DetailVideoActivity;
import com.example.utspam.Model.Videos;
import com.example.utspam.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListVideo extends RecyclerView.Adapter<AdapterListVideo.ViewHolder> {
    Context context;
    List<Videos> viewItems;

    public AdapterListVideo(Context context, List<Videos> viewItems) {
        this.context = context;
        this.viewItems = viewItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_video_layout, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //menambahkan "https://themoviedb.org/t/p/w500/" ke poster_path untuk dijadikan url
        String thumbnailUrl = "https://themoviedb.org/t/p/w500/"+viewItems.get(position).getPosterPath();
        Picasso.with(context).load(thumbnailUrl).fit().centerCrop().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(holder.icon);

        //mengambil title dari model videos
        String title = viewItems.get(position).getTitle();
        holder.txtTitle.setText(title);

        String overview = viewItems.get(position).getOverview();
        holder.txtOverview.setText(overview);

        String released = viewItems.get(position).getReleaseDate();
        String rilis = String.format(context.getResources().getString(R.string.released), released);
        holder.txtRelease.setText(rilis);

        String popularity = viewItems.get(position).getPopularity().toString();

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(context, DetailVideoActivity.class);
                Bundle b = new Bundle();
                b.putString("title", title);
                b.putString("released", released);
                b.putString("overview", overview);
                b.putString("popularity", popularity);
                b.putString("imageUrl", thumbnailUrl);
                intent.putExtras(b);
                context.startActivity(intent);
            }
        });

    }

    //mendapatkan jumlah dari item di json
    @Override
    public int getItemCount() {
        return viewItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView txtTitle, txtOverview, txtRelease;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtOverview = itemView.findViewById(R.id.txtOverview);
            txtRelease = itemView.findViewById(R.id.txtRelease);

        }
    }
}
