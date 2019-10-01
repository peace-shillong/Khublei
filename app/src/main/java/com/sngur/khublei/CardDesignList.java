package com.sngur.khublei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class CardDesignList extends AppCompatActivity {
    Bundle data;
    String language;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_design_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.khublei_hand);

        data=getIntent().getExtras();
        language=data.getString("Language");

        switch (language)
        {
            case "Khasi":
                setupKhasi();
                break;
            case "Garo":
                setupGaro();
                break;
            case "Hindi":
                setupHindi();
                break;
            case "English":
                setupEnglish();
                break;

        }
    }

    private void setupKhasi() {
    }

    private void setupGaro() {
    }

    private void setupHindi() {
    }

    private void setupEnglish() {
    }

    public void openInputData(View view) {
        int i=view.getId();
        Intent intent= new Intent(this,EnterMessageActivity.class);
        intent.putExtra("Language",language);
        switch (i)
        {
            case R.id.goodmorning:
                intent.putExtra("Card","Good Morning");
                break;
            case R.id.goodnight:
                intent.putExtra("Card","Good Night");
                break;
            case R.id.bcard1:
                intent.putExtra("Card","BCard1");
                break;
            case R.id.bcard2:
                intent.putExtra("Card","BCard2");
                break;
            case R.id.tcard1:
                intent.putExtra("Card","TCard1");
                break;
            case R.id.tcard2:
                intent.putExtra("Card","TCard2");
                break;
            case R.id.atbcard1:
                intent.putExtra("Card","ATBCard1");
                break;
            case R.id.atbcard2:
                intent.putExtra("Card","ATBCard2");
                break;
            case R.id.gwscard1:
                intent.putExtra("Card","GWSCard1");
                break;
            case R.id.gwscard2:
                intent.putExtra("Card","GWSCard2");
                break;
            case R.id.pbucard1:
                intent.putExtra("Card","PBWUCard1");
                break;
            case R.id.pbucard2:
                intent.putExtra("Card","PBWUCard2");
                break;
        }
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    //Back Button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
