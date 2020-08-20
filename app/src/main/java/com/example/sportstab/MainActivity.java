package com.example.sportstab;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the ViewPager and set it's PagetAdapter so that it can display items

        ViewPager vp = findViewById(R.id.viewpager);
        PagerAdapter pA = new PagerAdapter(getSupportFragmentManager());
        vp.setAdapter(pA);

        //give the tabLayout the ViewPager
        TabLayout tI = findViewById(R.id.sliding_tabs);
        tI.setupWithViewPager(vp);
    }

    public void onBackPressed()
    {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("앱을 종료하시겠습니까?").setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                MainActivity.this.finish();
            }
        });

        localBuilder.create().show();
    }
}
