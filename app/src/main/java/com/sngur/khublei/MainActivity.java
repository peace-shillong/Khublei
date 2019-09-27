package com.sngur.khublei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setIcon(R.drawable.khublei_hand);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.khublei_logo);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void openCardDesign(View view) {
        int i=view.getId();
        Intent intent= new Intent(this,CardDesignList.class);
        switch (i)
        {
            case R.id.khasi:
                intent.putExtra("Language","Khasi");
                break;
            case R.id.garo:
                intent.putExtra("Language","Garo");
                break;
            case R.id.pnar:
                intent.putExtra("Language","Pnar");
                break;
            case R.id.hindi:
                intent.putExtra("Language","Hindi");
                break;
            case R.id.english:
                intent.putExtra("Language","English");
                break;
        }
        startActivity(intent);
    }

    public void openAbout(View view) {
        Intent intent= new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
}
