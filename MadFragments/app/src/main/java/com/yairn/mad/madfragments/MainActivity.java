package com.yairn.mad.madfragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener,
        PicsFragment.OnPicsFragmentInteractionListener, FullPicFragment.OnFullImageFragmentInteractionListener{

    private ListFragment listFragment;
    private PicsFragment picsFragment;

    private FullPicFragment fullPicFragment;


    private int lastYear = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = new ListFragment();
        picsFragment = new PicsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.listFrame, listFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.pictureFrame, picsFragment).commit();

        fullPicFragment = null;
    }



    public void onListFragmentInteraction(int year) {
        lastYear = year;
        picsFragment.setPic(year);
    }

    public void onPicsFragmentInteraction(int id) {
        fullPicFragment = new FullPicFragment().newInstance(id);
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, fullPicFragment).remove(listFragment).remove(picsFragment).commit();
    }

    public void onFullImageFragmentInteraction(int id) {
        switch (id) {
            case R.drawable.mad_logo_14:
                lastYear = 0;
            case R.drawable.mad_logo_15:
                lastYear = 1;
            case R.drawable.mad_logo_16:
                lastYear = 2;
            case R.drawable.mad_logo_17:
                lastYear = 3;
            case R.drawable.mad_logo_18:
                lastYear = 4;
            default:

        }

        if(fullPicFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.listFrame, listFragment).replace(R.id.pictureFrame, picsFragment).remove(fullPicFragment).commit();
            picsFragment.setPic(lastYear);
        } else  {
            getSupportFragmentManager().beginTransaction().replace(R.id.listFrame, listFragment).replace(R.id.pictureFrame, picsFragment).commit();
            picsFragment.setPic(lastYear);
        }

    }
}
