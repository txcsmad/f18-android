package com.mad.yairn.infinitekittens2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

public class KittenItemHolder extends RecyclerView.ViewHolder {

    private ImageButton kittenItemImageView;
    private ImageButton favoriteImageButton;
    private ImageButton browserImageButton;

    public KittenItemHolder(View view){
        super(view);

        kittenItemImageView = view.findViewById(R.id.kittenItemImageView);
        favoriteImageButton = view.findViewById(R.id.favoriteImageButton);
        browserImageButton = view.findViewById(R.id.browserImageButton);
    }


}
