package com.cyanalumnidev.cyanalumni;

/**
 * Created by erdinandapandu on 30/03/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class ProfileActivity extends AppCompatActivity {

    ExpandableRelativeLayout expandableProfile, expandablePost, expandableContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

    }

    public void expandableButton_profile(View view) {
        expandableProfile = (ExpandableRelativeLayout) findViewById(R.id.layout_profile_expand);
        expandableProfile.toggle(); // toggle expand and collapse
    }

    public void expandableButton_post(View view) {
        expandablePost = (ExpandableRelativeLayout) findViewById(R.id.layout_post_expand);
        expandablePost.toggle(); // toggle expand and collapse
    }

    public void expandableButton_contact(View view) {
        expandableContact = (ExpandableRelativeLayout) findViewById(R.id.layout_contact_expand);
        expandableContact.toggle(); // toggle expand and collapse
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}

