package com.johntsai.superv2ex;

import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.johntsai.superv2ex.adapter.FragmentPagerAdapter;
import com.johntsai.superv2ex.ui.HotTopicFragment;
import com.johntsai.superv2ex.ui.LatestTopicFragment;
import com.johntsai.superv2ex.ui.NodeInfoFragment;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;

    private static final SparseIntArray array = new SparseIntArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        mBottomNavigationView = findViewById(R.id.bottom_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        array.append(0, R.id.action_hot_topic);
        array.append(1, R.id.action_latest_topic);
        array.append(2, R.id.action_node_info);

        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new HotTopicFragment());
        pagerAdapter.addFragment(new LatestTopicFragment());
        pagerAdapter.addFragment(new NodeInfoFragment());

        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(2);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_hot_topic:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.action_latest_topic:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.action_node_info:
                        mViewPager.setCurrentItem(2);
                        break;

                }
                return true;
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.setSelectedItemId(array.get(position));
            }
        });

    }
}
