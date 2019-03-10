package dk.flemminglarsen.easyfitplan.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import dk.flemminglarsen.easyfitplan.Helperclasses.BottomNavigationViewHelper;
import dk.flemminglarsen.easyfitplan.Fragments.EducationFragment;
import dk.flemminglarsen.easyfitplan.Fragments.ProfileFragment;
import dk.flemminglarsen.easyfitplan.Fragments.TrackingFragment;
import dk.flemminglarsen.easyfitplan.Fragments.TrainingFragment;
import dk.flemminglarsen.easyfitplan.R;

public class MenuActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_menu);

                BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
                bottomNav.setOnNavigationItemSelectedListener(navListener);
                BottomNavigationViewHelper.disableShiftMode(bottomNav);

                //If nothing is chosen, set fragmentcontainer to Profilefragment - ie. as the default
                if (savedInstanceState == null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ProfileFragment()).commit();

                }
        }

        //Switch for bottomnavigation
        private BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                Fragment selectedFragment = null;

                                switch (item.getItemId()) {
                                        case R.id.nav_profile:
                                                selectedFragment = new ProfileFragment();

                                                break;
                                        case R.id.nav_tracking:
                                                selectedFragment = new TrackingFragment();

                                                break;
                                        case R.id.nav_training:
                                                selectedFragment = new TrainingFragment();

                                                break;
                                        case R.id.nav_education:
                                                selectedFragment = new EducationFragment();

                                                break;
                                }

                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                        selectedFragment).commit();

                                return true;
                        }
                };

}