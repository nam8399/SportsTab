package com.example.sportstab;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ThirdFragment extends Fragment {
    private RecyclerView rv;
    private FavoriteAdapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_third, container, false);

        rv=(RecyclerView)root.findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        getFavData();

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getFavData() {
        List<FavoriteList> favoriteLists= FirstFragment.favoriteDatabase.favoriteDao().getFavoriteData();
        adapter=new FavoriteAdapter(favoriteLists,getContext());
        rv.setAdapter(adapter);
    }
}

