package com.example.sportstab;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<Product_List> product_lists;
    Context ct;

    public ProductAdapter(List<Product_List> product_lists, Context ct) {
        this.product_lists = product_lists;
        this.ct = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Product_List productList=product_lists.get(i);

        String pimg=productList.getImage();
        String pimg2=productList.getImage2();

        Picasso.with(ct).load(pimg).into(viewHolder.img);
        Picasso.with(ct).load(pimg2).into(viewHolder.img2);

        viewHolder.tv.setText(productList.getName());
        viewHolder.tv2.setText(productList.getName2());
        viewHolder.tv3.setText(productList.getName3());

        if (FirstFragment.favoriteDatabase.favoriteDao().isFavorite(productList.getId())==1)
            viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite);
        else
            viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite_border_black_24dp);


        viewHolder.fav_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                FavoriteList favoriteList=new FavoriteList();

                int id=productList.getId();
                String image=productList.getImage();
                String image2=productList.getImage2();
                String name=productList.getName();
                String name2=productList.getName2();
                String name3=productList.getName3();

                favoriteList.setId(id);
                favoriteList.setImage(image);
                favoriteList.setImage2(image2);
                favoriteList.setName(name);
                favoriteList.setName2(name2);
                favoriteList.setName3(name3);

                if (FirstFragment.favoriteDatabase.favoriteDao().isFavorite(id)!=1){
                    viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite);
                    FirstFragment.favoriteDatabase.favoriteDao().addData(favoriteList);

                }else {
                    viewHolder.fav_btn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    FirstFragment.favoriteDatabase.favoriteDao().delete(favoriteList);

                }


            }
        });
    }

    public void setItems(ArrayList<Product_List> product_lists) {
        this.product_lists = product_lists;
    }

    @Override
    public int getItemCount() {
        return product_lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img, img2, fav_btn;
        TextView tv,tv2, tv3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView)itemView.findViewById(R.id.img_pr);
            img2=(ImageView)itemView.findViewById(R.id.img_pr2);
            tv=(TextView)itemView.findViewById(R.id.tv_name);
            tv2=(TextView)itemView.findViewById(R.id.tv_name2);
            tv3=(TextView)itemView.findViewById(R.id.tv_name3);
            fav_btn=(ImageView)itemView.findViewById(R.id.fav_btn);

        }
    }

    public void nalzza(ArrayList<Product_List> arrayList )
    {
        product_lists.clear();
        product_lists.addAll(arrayList);
        notifyDataSetChanged();

    }
}
