package com.mad.yairn.madcardswipe;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.ArrayList;

public class CardSwipeActivity extends AppCompatActivity implements GetProfiles.GetProfilesCallback {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private ArrayList<Profile> mProfiles;

    public void onBackgroundCallComplete(ArrayList<Profile> profiles) {
        mProfiles = profiles;
        onCreateProfileCards();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_swipe);

//        GetProfiles getProfiles = new GetProfiles(this).execute();
//        getProfiles.execute();

        mSwipeView = (SwipePlaceHolderView) findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                    .setPaddingTop(20)
                    .setRelativeScale(0.01f)
                    .setSwipeInMsgLayoutId(R.layout.card_swipe_accept_message)
                    .setSwipeOutMsgLayoutId(R.layout.card_swipe_reject_message));

        new GetProfiles(this).execute();
    }

    private void onCreateProfileCards() {
        for(int index = 0; index < mProfiles.size(); index++){
            mSwipeView.addView(new CardItemViewHolder(mContext, mProfiles.get(index), mSwipeView));
        }

        findViewById(R.id.rejectButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });
    }
}
