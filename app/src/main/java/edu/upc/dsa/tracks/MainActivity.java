package edu.upc.dsa.tracks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        mCurrentFragment = new TracksFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, mCurrentFragment).commit();
        bottomNavigationView.setSelectedItemId(R.id.tracks);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        mCurrentFragment = new AddFragment();
                        break;
                    case R.id.tracks:
                        mCurrentFragment = new TracksFragment();
                        break;
                    case R.id.delete:
                        mCurrentFragment = new DeleteFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, mCurrentFragment).commit();
                return true;
            }
        });
    }
}