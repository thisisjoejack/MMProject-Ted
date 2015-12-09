package com.money.mmproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.money.mmproject.Fragment.*;


public class NavigateFragment extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String ARG_SECTION_NUMBER = "section number";

    /*Fragment managing the behaviors, interactions and presentation of the navigation drawer. */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /*Used to store the last screen title. For use in {@link #restoreActionBar()}. */
    private CharSequence mTitle;
    private String codeUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_navigator);



        /*
         Intent mIntent = getIntent();
        int receivedInt = mIntent.getIntExtra(ARG_SECTION_NUMBER, sectionNumber);
        System.out.println("received section number of " + receivedInt + " from intent");

         */

        mNavigationDrawerFragment = (NavigationDrawerFragment)  getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));




    }

    /*  new String[]{
        "daily",        0
        "weekly",       1
        "monthly",      2
        "annually",     3
        "minor expense",4
        "major expense" 5
        }
    */

    private Fragment getFragmentInstance(int sectionNumber) {



        Fragment fragment;
        if (sectionNumber == 0) {
            fragment = new SimpleLineGraphDaily();
        } else if (sectionNumber == 1) {
            fragment = new SimpleLineGraphWeekly();
        } else if (sectionNumber == 2) {
            fragment = new SimpleLineGraphMonthly();
        } else if (sectionNumber == 3) {
            fragment = new SimpleLineGraphAnnually();
        } else if (sectionNumber == 4) {
            fragment = new SimpleLineGraphMinorExpense();
        }   else if (sectionNumber == 5) {
            fragment = new SimpleLineGraphMajorExpense();
        }


        else {
            throw new IllegalStateException("unknown section "+sectionNumber);
        }
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, getFragmentInstance(position)).commit();
    }


    public void onSectionAttached(int number) {
    }


    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

}


