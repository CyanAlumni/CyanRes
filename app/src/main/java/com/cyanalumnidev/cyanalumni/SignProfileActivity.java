package com.cyanalumnidev.cyanalumni;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by CyanApps on 28/03/2017.
 */

public class SignProfileActivity extends AppCompatActivity {

    private ImageButton mSign_photo;
    private EditText msign_name;
    private EditText msign_graduatedYear;
    private EditText msign_occupation;
    private EditText msign_company;
    private EditText msign_address;
    private EditText msign_city;
    private EditText msign_email;
    private Button mbtn_update;

    private Uri mImageUri = null;

    private static final int GALLERY_REQUEST = 1;
    private StorageReference mStorage;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_profile);



        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Profile");

        mSign_photo= (ImageButton) findViewById(R.id.sign_photo);

        msign_name = (EditText) findViewById(R.id.sign_name);
        msign_graduatedYear = (EditText) findViewById(R.id.sign_graduatedYear);
        msign_occupation = (EditText) findViewById(R.id.sign_occupation);
        msign_company = (EditText) findViewById(R.id.sign_company);
        msign_address = (EditText) findViewById(R.id.sign_address);
        msign_city = (EditText) findViewById(R.id.sign_city);
        msign_email = (EditText) findViewById(R.id.sign_email);

        mbtn_update = (Button) findViewById(R.id.btn_update);
        mProgress = new ProgressDialog(this);

        mSign_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

            }
        });

        mbtn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }
    private void startPosting(){

        mProgress.setMessage("Update Your  Profile ...");
        mProgress.show();
        final  String fsign_name = msign_name.getText().toString().trim();
        final String fsign_graduatedYear = msign_graduatedYear.getText().toString().trim();
        final  String fsign_occupation = msign_occupation.getText().toString().trim();
        final String fsign_company = msign_company.getText().toString().trim();
        final  String fsign_address = msign_address.getText().toString().trim();
        final String fsign_city = msign_city.getText().toString().trim();
        final  String fsign_email = msign_email.getText().toString().trim();

        if (!TextUtils.isEmpty(fsign_name)&& !TextUtils.isEmpty(fsign_graduatedYear)&& !TextUtils.isEmpty(fsign_occupation)&& !TextUtils.isEmpty(fsign_company)&&         !TextUtils.isEmpty(fsign_address)&& !TextUtils.isEmpty(fsign_city) && !TextUtils.isEmpty(fsign_email)&& mImageUri != null) {
            StorageReference filepath = mStorage.child("Blog_Images").child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("Name").setValue(fsign_name);
                    newPost.child("Graduated_Year").setValue(fsign_graduatedYear);
                    newPost.child("Occupation").setValue(fsign_occupation);
                    newPost.child("Company").setValue(fsign_company);
                    newPost.child("Address").setValue(fsign_address);
                    newPost.child("City").setValue(fsign_city);
                    newPost.child("Email").setValue(fsign_email);
                    newPost.child("image").setValue(downloadUri.toString());

                    mProgress.dismiss();
                    startActivity(new Intent(SignProfileActivity.this, Main2Activity.class));
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri = data.getData();
            mSign_photo.setImageURI(mImageUri);

        }
    }
}
