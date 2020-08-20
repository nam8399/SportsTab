package com.example.sportstab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class SecondFragment extends Fragment {

    GridLayout mainGridLayout;

    public SecondFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        mainGridLayout = (GridLayout)view.findViewById(R.id.mainGridLayout);

        setSingleEvent(mainGridLayout);

        setHasOptionsMenu(true);


        return view;
    }
    private void setSingleEvent(final GridLayout mainGridLayout) {

        ImageView imageView;
        TextView textView;
        Button favBtn;

          for (int i = 0; i < mainGridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) mainGridLayout.getChildAt(i);

            final int final1 = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (final1 == 0) {
                        Intent intent = new Intent(SecondFragment.this.getContext(), EplActivity.class);
                        startActivity(intent);
                    } else if (final1 == 1) {
                        Intent intent = new Intent(SecondFragment.this.getContext(), LaligaActivity.class);
                        startActivity(intent);
                    } else if (final1 == 2) {
                        Intent intent = new Intent(SecondFragment.this.getContext(), SeriaActivity.class);
                        startActivity(intent);
                    } else if (final1 == 3) {
                        Intent intent = new Intent(SecondFragment.this.getContext(), BundesActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }



    }


}