package com.fiek.projektineandroid;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int nrFragmenteve;

    public PagerAdapter(FragmentManager fm, int numriFragmenteve){
        super(fm);
        this.nrFragmenteve=numriFragmenteve;

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Fragmenti1 fragmenti1= new Fragmenti1();
                return fragmenti1;
            case 1:
                Fragmenti2 fragmenti2= new Fragmenti2();
                return fragmenti2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 0;
    }
}
