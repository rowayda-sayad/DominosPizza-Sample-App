package com.example.dominospizzaudemy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab);
        ViewPager viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        BestSellerFragment bestSellerFragment = new BestSellerFragment();
        PastaPizzaFragment pastaPizzaFragment = new PastaPizzaFragment();
        VegPizzaFragment vegPizzaFragment = new VegPizzaFragment();
        NonVegPizzaFragment nonVegPizzaFragment = new NonVegPizzaFragment();

        ArrayAdapterFragment arrayAdapterFragment = new ArrayAdapterFragment(getSupportFragmentManager(), 0);
        arrayAdapterFragment.addFragment(bestSellerFragment, "BESTSELLERS");
        arrayAdapterFragment.addFragment(pastaPizzaFragment, "PASTA PIZZA");
        arrayAdapterFragment.addFragment(vegPizzaFragment, "VEG PIZZA");
        arrayAdapterFragment.addFragment(nonVegPizzaFragment, "NON-VEG PIZZA");
        viewPager.setAdapter(arrayAdapterFragment);

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


}

class ArrayAdapterFragment extends FragmentPagerAdapter {

    List<Fragment> fragmentArrayList = new ArrayList<>();
    List<String> fragmentTitles = new ArrayList<>();

    public ArrayAdapterFragment(FragmentManager fm, int behaviour) {
        super(fm, behaviour);
    }

    void addFragment(Fragment f, String title) {
        fragmentArrayList.add(f);
        fragmentTitles.add(title);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitles.get(position);
    }
}