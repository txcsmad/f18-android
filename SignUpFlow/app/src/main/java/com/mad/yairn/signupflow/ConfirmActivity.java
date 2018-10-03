package com.mad.yairn.signupflow;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    private ImageView picImageView;
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView genderTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        picImageView = findViewById(R.id.picImageView);
        nameTextView = findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        genderTextView = findViewById(R.id.genderTextView);

        Bundle extras = getIntent().getExtras();
        Bitmap bitmap = getIntent().getParcelableExtra("Picture");
        picImageView.setImageBitmap(bitmap);
        nameTextView.setText(extras.getString("Name"));
        emailTextView.setText(extras.getString("Email"));
        genderTextView.setText(extras.getString("Gender"));
    }

    public void onClickConfirm(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
