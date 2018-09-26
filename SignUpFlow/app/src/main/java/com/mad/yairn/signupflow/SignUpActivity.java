package com.mad.yairn.signupflow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.support.design.widget.Snackbar;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private static final int ALL_PERM_GRANTED = 0;
    private static final int REQ_TAKE_PIC = 1;


    private ImageButton addPhotoButton;
    private EditText nameEditTextView;
    private EditText emailEditTextView;
    private Spinner genderSpinner;

    private Bitmap bitmapPicture = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        addPhotoButton = findViewById(R.id.addPhotoButton);
        nameEditTextView = findViewById(R.id.nameEditText);
        emailEditTextView = findViewById(R.id.emailEditText);
        genderSpinner = findViewById(R.id.genderSpinner);

        addPhotoButton.setBackgroundResource(R.drawable.ic_add_photo);
    }


    public void onClickPic(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        checkPermissions();

        if(intent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }

            if(photoFile != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile);
                startActivityForResult(intent, REQ_TAKE_PIC);
            }
        }
    }

    //     in build.gradle(app): implementation 'com.android.support:design:27.1.1'

    //     import android.support.design.widget.Snackbar;
    public void onClickNext(View view) {
        String firstName = nameEditTextView.getText().toString();
        String email = emailEditTextView.getText().toString();

        if(firstName.length() > 0 && email.length() > 0 &&
                genderSpinner.getSelectedItemPosition() > 0 &&
                bitmapPicture != null) {

        } else {
            Snackbar.make(getCurrentFocus(), "One or more fields are missing",
                    Snackbar.LENGTH_LONG).show();
        }

    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);

        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_TAKE_PIC && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            bitmapPicture =imageBitmap;
            addPhotoButton.setImageBitmap(bitmapPicture);
        }
    }

    private void checkPermissions() {
        String[] permissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        for(String permission: permissions) {
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission}, ALL_PERM_GRANTED);
            }
        }

    }
}
