package com.example.fragmentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

//import androidx.appcompat.app.AppCompatActivity;
//
//
//    public class MainActivity extends AppCompatActivity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//
//            // Add Fragment1 to the container
//            if (savedInstanceState == null) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, new fragment_1())
//                        .commit();
//            }
//        }
//
//        // Handle button click to replace with Fragment2 (optional)
//        public void showFragment2(View view) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new fragment_2())
//                    .addToBackStack(null) // Add to back stack for navigation
//                    .commit();
//        }
//
//
//    };

public class MainActivity extends AppCompatActivity {

    private Button showFragment2Button;
    private Button showFragment1Button;
    private Fragment currentFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment2Button = findViewById(R.id.show_fragment_2_button);
        showFragment1Button = findViewById(R.id.show_fragment_1_button);

        // Add Fragment1 to the container (assuming there's no saved state)
        currentFragment = new fragment_1();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, currentFragment)
                .commit();

        // Update button state based on current fragment
        updateButtonState();

        // Handle button click to show Fragment2
        showFragment2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFragment instanceof fragment_1) {
                    // Replace with Fragment2
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new fragment_2())
                            .addToBackStack(null)
                            .commit();
                    currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    updateButtonState();
                }
            }
        });

        // Handle button click to show Fragment1
        showFragment1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFragment instanceof fragment_2) {
                    // Replace with Fragment1
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new fragment_1())
                            .addToBackStack(null)
                            .commit();
                    currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                    updateButtonState();
                }
            }
        });
    }

    private void updateButtonState() {
        if (currentFragment instanceof fragment_1) {
            showFragment2Button.setEnabled(true);
            showFragment1Button.setEnabled(false);
        } else {
            showFragment2Button.setEnabled(false);
            showFragment1Button.setEnabled(true);
        }
    }
}
