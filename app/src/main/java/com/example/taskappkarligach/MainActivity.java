package com.example.taskappkarligach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.profile_fragment)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.navigation_home || destination.getId() == R.id.navigation_dashboard ||
                    destination.getId() == R.id.navigation_notifications || destination.getId() == R.id.profile_fragment)
                navView.setVisibility(android.view.View.VISIBLE);
            else navView.setVisibility(android.view.View.GONE);
            if (destination.getId() == R.id.boardFragment)
                Objects.requireNonNull(getSupportActionBar()).hide();
            else Objects.requireNonNull(getSupportActionBar()).show();
        });


        Prefs prefs = new Prefs(this);
        if (!prefs.getBoardState()){
            navController.navigate(R.id.boardFragment);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}