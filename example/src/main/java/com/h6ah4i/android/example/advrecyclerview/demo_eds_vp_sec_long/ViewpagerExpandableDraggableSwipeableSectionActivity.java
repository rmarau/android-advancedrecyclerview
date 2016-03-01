/*
 *    Copyright (C) 2015 Haruki Hasegawa
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.h6ah4i.android.example.advrecyclerview.demo_eds_vp_sec_long;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.h6ah4i.android.example.advrecyclerview.R;
import com.h6ah4i.android.example.advrecyclerview.common.data.AbstractExpandableDataProvider;
import com.h6ah4i.android.example.advrecyclerview.common.fragment.ExampleSectionExpandableDataProviderFragment;


public class ViewpagerExpandableDraggableSwipeableSectionActivity extends AppCompatActivity {
    public static final String FRAGMENT_TAG_DATA_PROVIDER_1 = "data provider 1";
    public static final String FRAGMENT_TAG_DATA_PROVIDER_2 = "data provider 2";

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_viewpager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mTabLayout.setupWithViewPager(mViewPager);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(new ExampleSectionExpandableDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER_1)
                    .add(new ExampleSectionExpandableDataProviderFragment(), FRAGMENT_TAG_DATA_PROVIDER_2)
                    .commit();
        }
    }

    public AbstractExpandableDataProvider getDataProvider(String dataProviderName) {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(dataProviderName);
        return ((ExampleSectionExpandableDataProviderFragment) fragment).getDataProvider();
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return RecyclerListViewPageFragment.newInstance(
                            ViewpagerExpandableDraggableSwipeableSectionActivity.FRAGMENT_TAG_DATA_PROVIDER_1);
                case 1:
                    return RecyclerListViewPageFragment.newInstance(
                            ViewpagerExpandableDraggableSwipeableSectionActivity.FRAGMENT_TAG_DATA_PROVIDER_2);
                default:
                    throw new IllegalStateException();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + (position + 1);
        }
    }

}
