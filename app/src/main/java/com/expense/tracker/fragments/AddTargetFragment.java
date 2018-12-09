package com.expense.tracker.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.expense.tracker.MainActivity;
import com.expense.tracker.R;

import java.util.ArrayList;
import java.util.List;

public class AddTargetFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_add_target, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((MainActivity) getActivity()).setBackButton();
            ((MainActivity) getActivity()).setTiltle("Add Target");
        }
        final ViewPager viewPager = view.findViewById(R.id.add_viewpager);
        setupViewPager(viewPager);
        final TabLayout tabLayout = view.findViewById(R.id.add_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        AddTargetFragment.ViewPagerAdapter adapter = new AddTargetFragment.ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AddCategoryFragment(), "Categories");
        adapter.addFragment(new AddPocketFragment(), "Pockets");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }



        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onDestroy() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).disableBackButton();
            ((MainActivity) getActivity()).setTiltle("Expense Tracker");
        }
        super.onDestroy();
    }

}
