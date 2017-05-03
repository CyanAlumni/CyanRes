package com.cyanalumnidev.cyanalumni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PostActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
