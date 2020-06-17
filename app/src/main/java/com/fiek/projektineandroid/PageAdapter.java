package com.fiek.projektineandroid;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class PageAdapter extends FragmentStatePagerAdapter {

    int nrFragmenteve;

    public PageAdapter(FragmentManager fm, int NumriFragmenteve)
    {
        super(fm);
        this.nrFragmenteve = NumriFragmenteve;

    }


    @NonNull
    @Override
    public Fragment getItem(int pozicioni) {

        switch(pozicioni)
        {

            case 0:
                Fragmenti1 f1 = new Fragmenti1();
                return f1;
            case 1:
                Fragmenti2 f2 = new Fragmenti2();
                return f2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return nrFragmenteve;
    }
}
