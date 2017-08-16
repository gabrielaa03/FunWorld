package com.gabrielaangebrandt.funworld.main_activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.gabrielaangebrandt.funworld.R;
import com.gabrielaangebrandt.funworld.base.SharedPrefs;
import com.gabrielaangebrandt.funworld.login_activity.view.Login;
import com.gabrielaangebrandt.funworld.main_activity.fragments.FragmentCountries;
import com.gabrielaangebrandt.funworld.main_activity.fragments.FragmentGames;
import com.gabrielaangebrandt.funworld.models.data_model.Player;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    fragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        ButterKnife.bind(this);
        tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.colorAccent));
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        Realm realm = Realm.getDefaultInstance();
        Player player = realm.where(Player.class).equalTo("username", SharedPrefs.getDefaults("username", this)).findFirst();
        if(player !=null){
            setTitle("Welcome, " + player.getName());
        }
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
            SharedPrefs.setDefaults("isLoggedIn", "out", this);
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
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

    //onemogućiti back button
    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    // funkcija za postavljanje adaptera za tabove
    private void setUpViewPager(ViewPager viewPager) {
        fragmentPagerAdapter adapter = new fragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCountries());
        adapter.addFragment(new FragmentGames());
        viewPager.setAdapter(adapter);
    }
}
