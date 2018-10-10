package com.mad.yairn.infinitekittens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class KittenImageActivity extends AppCompatActivity {

    private ImageView kittenImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_kitten_image);

        Intent intent = getIntent();
        String kittenUrl = intent.getStringExtra("kittenUrl");

        kittenImageView = findViewById(R.id.kittenImageView);

        if(kittenUrl != null)
            Picasso.get().load(kittenUrl).fit().into(kittenImageView);
    }
}
