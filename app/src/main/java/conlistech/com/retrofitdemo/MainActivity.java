package conlistech.com.retrofitdemo;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView responseText;
    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    TextView tvMenu1, tvMenu2, tvMenu3, tvMenu4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Checking for the Tablet Layout
        if (findViewById(R.id.layout_tablet) != null) {
            Toast.makeText(this,
                    "Tablet Layout loaded",
                    Toast.LENGTH_LONG).show();

            tvMenu1 = (TextView) findViewById(R.id.tv1);

            tvMenu2 = (TextView) findViewById(R.id.tv10);

            tvMenu3 = (TextView) findViewById(R.id.tv25);

            tvMenu4 = (TextView) findViewById(R.id.tv50);

            tvMenu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create new fragment and transaction
                    Fragment newFragment = new FirstFragment();
                    FragmentTransaction transaction = getSupportFragmentManager()
                            .beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }
            });

            tvMenu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Create new fragment and transaction
                    Fragment newFragment = new SecondFragment();
                    FragmentTransaction transaction = getSupportFragmentManager()
                            .beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();

                }
            });

            tvMenu3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create new fragment and transaction
                    Fragment newFragment = new ThirdFragment();
                    FragmentTransaction transaction = getSupportFragmentManager()
                            .beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }
            });

            tvMenu4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Create new fragment and transaction
                    Fragment newFragment = new FourthFragment();
                    FragmentTransaction transaction = getSupportFragmentManager()
                            .beginTransaction();

                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack
                    transaction.replace(R.id.content_frame, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();

                }
            });
        }
        // Checking for the Phone Layout
        else if (findViewById(R.id.drawer_layout) != null) {
            mDrawerLayout = findViewById(R.id.drawer_layout);
            drawerToggle = setupDrawerToggle();
            NavigationView navigationView = findViewById(R.id.nav_view);
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBar actionbar = getSupportActionBar();
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // set item as selected to persist highlight
                            menuItem.setChecked(true);
                            selectDrawerItem(menuItem);
                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();
                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here
                            return true;
                        }
                    });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_camera:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_gallery:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_slideshow:
                fragmentClass = ThirdFragment.class;
                break;
            case R.id.nav_manage:
                fragmentClass = FourthFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if (findViewById(R.id.drawer_layout) != null) {
            drawerToggle.syncState();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        if (findViewById(R.id.drawer_layout) != null) {
            drawerToggle.onConfigurationChanged(newConfig);
        }


    }

}



