package com.shilpa.TwitterSearch.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.shilpa.TwitterSearch.R;
import com.shilpa.TwitterSearch.base.activty.TwitterBaseActivity;
import com.shilpa.TwitterSearch.home.HomePresenter;
import com.shilpa.TwitterSearch.home.HomePresenterImpl;
import com.shilpa.TwitterSearch.home.HomeView;
import com.shilpa.TwitterSearch.model.SearchResponseData;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends TwitterBaseActivity implements NavigationView.OnNavigationItemSelectedListener, HomeView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.editSearch)
    EditText editSearch;
    @BindView(R.id.list_view)

    ListView listView;
    @BindView(R.id.search)
    Button searchData;
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        homePresenter = new HomePresenterImpl(this);
    }
    public void onsearchButtonClicked(View view) {
        showLoading();
        String searchText = editSearch.getText().toString();

        homePresenter.searchData(searchText);
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about_school) {
            // Handle the action
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onRequestFailure(String message) {

    }

    @Override
    public void onRequestSuccess() {

    }

    @Override
    public void onRequestSuccess(ArrayList<SearchResponseData> tweets) {
       ArrayList<String> tweetTest=new ArrayList<String>();
        for(int i=0;i<tweets.size();i++)
        {
            tweetTest.add(tweets.get(i).getText());
        }
        ArrayAdapter<String> test = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tweetTest);

listView.setAdapter(test);

    }


}
