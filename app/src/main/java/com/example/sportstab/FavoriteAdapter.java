package com.example.sportstab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private List<FavoriteList> favoriteLists;
    Context context;

    public FavoriteAdapter(List<FavoriteList> favoriteLists, Context context) {
        this.favoriteLists = favoriteLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.favorite_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FavoriteList fl=favoriteLists.get(i);
        Picasso.with(context).load(fl.getImage()).into(viewHolder.img);
        Picasso.with(context).load(fl.getImage2()).into(viewHolder.img2);
        viewHolder.tv.setText(fl.getName());
        viewHolder.tv2.setText(fl.getName2());
        viewHolder.tv3.setText(fl.getName3());
    }

    @Override
    public int getItemCount() {
        return favoriteLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img, img2;
        TextView tv, tv2, tv3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.fimg_pr);
            img2=(ImageView)itemView.findViewById(R.id.fimg_pr2);
            tv=(TextView)itemView.findViewById(R.id.ftv_name);
            tv2=(TextView)itemView.findViewById(R.id.ftv_name2);
            tv3=(TextView)itemView.findViewById(R.id.ftv_name3);
        }
    }
}
