package com.gabrielaangebrandt.funworld.MainActivity.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gabrielaangebrandt.funworld.LauncherActivity.view.Login;
import com.gabrielaangebrandt.funworld.MainActivity.fragments.FragmentCountries;
import com.gabrielaangebrandt.funworld.MainActivity.fragments.FragmentGames;
import com.gabrielaangebrandt.funworld.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    TabLayout tabLayout;
    ViewPager viewPager;
    fragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        this.setUp();
    }

    private void setUp() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.colorAccent));
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        pagerAdapter = new fragmentPagerAdapter(getSupportFragmentManager());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(
                tabLayout));
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out_button, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mybutton) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            prefs.edit().putBoolean("Islogin", false).apply();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }



    // definiranje adaptera za tabove

    public class fragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();

        public fragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getText(R.string.countries);
                case 1:
                    return getText(R.string.games);
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            /*switch(position){
                case 0:
                    return new FragmentCountries();


                case 1:
                    return new FragmentGames();

            }return null;*/

            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

    }

    // funkcija za postavljanje adaptera za tabove
    private void setUpViewPager(ViewPager viewPager) {
        fragmentPagerAdapter adapter = new fragmentPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentCountries());
        adapter.addFragment(new FragmentGames());

        viewPager.setAdapter(adapter);

    }


}
