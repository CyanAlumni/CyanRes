package com.cyanalumnidev.cyanalumni;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.cyanalumnidev.cyanalumni.bb.fragment.InternshipFragment;
import com.cyanalumnidev.cyanalumni.bb.fragment.JobVacancyFragment;
import com.cyanalumnidev.cyanalumni.bb.fragment.OurAlumniFragment;
import com.cyanalumnidev.cyanalumni.bb.fragment.ProjectFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

//==================================================================================================================================================


        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.menu_bb, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int i) {
                if (i == R.id.bb_ouralumni) {
                    OurAlumniFragment f = new OurAlumniFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_bb, f).commit();
                } else if (i == R.id.bb_jobVacancy) {
                    JobVacancyFragment f = new JobVacancyFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_bb, f).commit();
                } else if (i == R.id.bb_internship) {
                    InternshipFragment f = new InternshipFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_bb, f).commit();
                } else if (i == R.id.bb_project) {
                    ProjectFragment f = new ProjectFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_bb, f).commit();
                } else if (i == R.id.bb_login) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int i) {

            }
        });
        mBottomBar.mapColorForTab(0, "#0097a7");
        mBottomBar.mapColorForTab(1, "#C23B22");
        mBottomBar.mapColorForTab(2, "#03C03C");
        mBottomBar.mapColorForTab(3, "#966FD6");
        mBottomBar.mapColorForTab(4, "#FFB347");
        mBottomBar.setActiveTabColor("#f2fe71");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bb, menu);
        return true;
    }


}
