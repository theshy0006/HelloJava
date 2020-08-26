package com.boc.hellojava.module.root;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.boc.hellojava.R;
import com.boc.hellojava.base.BaseActivity;
import com.boc.hellojava.module.cart.CartFragment;
import com.boc.hellojava.module.cate.CateFragment;
import com.boc.hellojava.module.home.HomeFragment;
import com.boc.hellojava.module.mine.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.vp_home_pager)
    ViewPager mViewPager;

    @BindView(R.id.bv_home_navigation)
    BottomNavigationView mBottomNavigationView;

    private ArrayList<Fragment> mFragments;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private MyAdapter myAdapter;

    @Override
    protected void initData() {
        super.initData();
        mFragments = new ArrayList<>();
        HomeFragment homeThreeFragment = new HomeFragment();
        CateFragment categoryThreeFragment = new CateFragment();
        CartFragment serviceThreeFragment = new CartFragment();
        MineFragment mineThreeFragment = new MineFragment();
        mFragments.add(homeThreeFragment);
        mFragments.add(categoryThreeFragment);
        mFragments.add(serviceThreeFragment);
        mFragments.add(mineThreeFragment);
    }

    @Override
    protected void initView() {
        super.initView();
        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        myAdapter = new MyAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myAdapter);
        mViewPager.setOffscreenPageLimit(myAdapter.getCount());
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mBottomNavigationView.setSelectedItemId(position);
        mBottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.menu_cate:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.menu_cart:
                mViewPager.setCurrentItem(2);
                return true;
            case R.id.menu_mine:
                mViewPager.setCurrentItem(3);
                return true;
            default:
                break;
        }
        return false;
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager manager) {
            this(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        public MyAdapter(FragmentManager manager, int behavior) {
            super(manager, behavior);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }
}