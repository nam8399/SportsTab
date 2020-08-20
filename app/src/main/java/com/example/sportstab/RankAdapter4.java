package com.example.sportstab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RankAdapter4 extends RecyclerView.Adapter<RankAdapter4.ViewHolder> {
    private ArrayList<Rank_List> rank_lists = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rank_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Rank_List fl=rank_lists.get(i);
        Glide.with(viewHolder.itemView.getContext())
                .load(fl.getImage())
                .into(viewHolder.img);
        viewHolder.tv.setText(fl.getName());
        viewHolder.tv2.setText(fl.getNum());
        viewHolder.tv3.setText(fl.getNum2());
        viewHolder.tv4.setText(fl.getNum3());
        viewHolder.tv5.setText(fl.getNum4());
        viewHolder.tv6.setText(fl.getNum5());
        viewHolder.tv7.setText(fl.getNum6());
        viewHolder.tv10.setText(fl.getNum9());
    }

    @Override
    public int getItemCount() {
        return rank_lists.size();
    }

    public void setItems(ArrayList<Rank_List> rank_lists) {
        this.rank_lists = rank_lists;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView tv, tv2, tv3, tv4, tv5, tv6, tv7, tv10;

        ViewHolder(View itemView) {
            super(itemView);

            img=(ImageView)itemView.findViewById(R.id.img_rk);
            tv=(TextView)itemView.findViewById(R.id.rk_name);
            tv2=(TextView)itemView.findViewById(R.id.rk_num);
            tv3=(TextView)itemView.findViewById(R.id.rk_num2);
            tv4=(TextView)itemView.findViewById(R.id.rk_num3);
            tv5=(TextView)itemView.findViewById(R.id.rk_num4);
            tv6=(TextView)itemView.findViewById(R.id.rk_num5);
            tv7=(TextView)itemView.findViewById(R.id.rk_num6);
            tv10=(TextView)itemView.findViewById(R.id.rk_num9);
        }
    }
}
