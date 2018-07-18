package com.nevictus.android.coolmix;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    //private ViewPager sNhViewPager;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inform the program to use our custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Reference the drawer layout view id
        drawer = findViewById(R.id.drawer_layout);

        //For the onClickLister using id nav_view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Draw the actionBar hamburger drawer, with animation , all the good stuff
         ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        //Set a default Color for the Drawer
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.brown_txt));
        //To re-draw the hamburger icon rotation & the drawer is opening
        toggle.syncState();

        //Check if null meaning starting for the first time in this case load the fragment.
        // if we rotate the device while fragment was already created don't recreate it.
        if (savedInstanceState == null) {
            //Display this fragment immediately as our first screen
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ManchesterNhFragment()).commit();

            //Automatically open/select the the drawer's message item
            navigationView.setCheckedItem(R.id.nav_manchester);
        }
    }

    //This is to handle the back button press to close the draw when it's open
    // but prevent it to close the activity itself
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Drawer menu Item's click navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            //Southern NH Main City & Towns Section
            case R.id.nav_manchester:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ManchesterNhFragment()).commit();
                break;
            case R.id.nav_bedford:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BedfordNhFragment()).commit();
                break;

            case R.id.nav_merrimack:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MerrimackNhFragment()).commit();
                break;
            case R.id.nav_hooksett:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HooksettNhFragment()).commit();
                break;

                //Things to do Section
            case R.id.nav_museums :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhMuseumsFragment()).commit();
                break;

            case R.id.nav_golf_country_clubs :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhSportClubsFragment()).commit();
                break;

            case R.id.nav_performArts :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhPerformingArtsFragment()).commit();
                break;

            case R.id.nav_dining :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhDiningFragment()).commit();
                break;

            case R.id.nav_libraries :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhPublicLibraryFragment()).commit();
                break;

            case R.id.nav_shopping :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhShoppingFragment()).commit(); //update here
                break;

            case R.id.nav_walking :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhWalkingFragment()).commit(); //update here
                break;

            case R.id.nav_parks :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhParksFragment()).commit(); //update here
                break;

                //Transportation  Section
            case R.id.nav_airport :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhAirportFragment()).commit(); //update here
                break;


                //Hotels & Bed n Breakfast Section
            case R.id.nav_hotels :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SnhHotelsFragment()).commit(); //update here
                break;
        }
        //We need to close the drawer
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }//end on create
}// end class