package com.filzah.moviedbsub3duty;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.filzah.moviedbsub3duty.views.FragmentMovie;
import com.filzah.moviedbsub3duty.views.FragmentPopular;
import com.filzah.moviedbsub3duty.views.FragmentTvshow;
import com.filzah.moviedbsub3duty.views.FragmentUpcoming;

public class SectionViewPager extends FragmentPagerAdapter {
    private final Context mcontext;

    @StringRes
    private static final int[] DRA_TITLES = new int[]{R.string.title_movie, R.string.title_tvshow, R.string.title_upcoming, R.string.title_popular};

    public SectionViewPager( Context mcontext, FragmentManager fm) {
        super(fm);
        this.mcontext = mcontext;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        switch (i){
            case 0 :
                fragment = new FragmentMovie();
                break;
            case 1 :
                fragment = new FragmentTvshow();
                break;
            case 2 :
                fragment = new FragmentUpcoming();
                break;
            case 3 :
                fragment = new FragmentPopular();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mcontext.getResources().getString(DRA_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
