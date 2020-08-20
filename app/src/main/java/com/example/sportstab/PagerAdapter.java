package com.example.sportstab;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    final int pageCount = 4;
    private String tabtitles[] = new String[]{"홈","경기 일정", "리그 별 팀순위", "즐겨찾기"};

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new ZeroFragment();
            case 1:
                return new FirstFragment();
            case 2:
                return new SecondFragment();
            case 3:
                return new ThirdFragment();
                default:
                    return null;

        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}
