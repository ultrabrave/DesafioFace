package com.desafiolatam.desafioface.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Switch;

import com.desafiolatam.desafioface.views.main.favorites.FavoritesFragment;
import com.desafiolatam.desafioface.views.main.users.UsersFragment;

/**
 * Created by QA on 24-11-2016.
 */

public class SectionsPagersAdapter extends FragmentPagerAdapter {
    public SectionsPagersAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return UsersFragment.newInstance();
            case 1:
                return FavoritesFragment.newInstance();
            default:
                return UsersFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Usuarios";
            case 1:
                return "Favoritos";
            default: return "Usuarios";
        }
    }
}
