package com.sngur.khublei;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class CardDesignList extends AppCompatActivity {
    private Bundle data;
    private String language;
    private ImageView bcard1,bcard2,tcard1,tcard2,gcard1,gcard2,gwscard1,gwscard2,atbcard1,atbcard2,pcard1,pcard2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_design_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.khublei_hand);
        //Step 1 Get Extra data from Intent
        data=getIntent().getExtras();
        language=data.getString("Language");
        //Step 2 Assign Objects to XML Views
        bcard1=findViewById(R.id.bcard1);
        bcard2=findViewById(R.id.bcard2);
        tcard1=findViewById(R.id.tcard1);
        tcard2=findViewById(R.id.tcard2);
        gcard1=findViewById(R.id.goodmorning);
        gcard2=findViewById(R.id.goodnight);
        gwscard1=findViewById(R.id.gwscard1);
        gwscard2=findViewById(R.id.gwscard2);
        atbcard1=findViewById(R.id.atbcard1);
        atbcard2=findViewById(R.id.atbcard2);
        pcard1=findViewById(R.id.pbucard1);
        pcard2=findViewById(R.id.pbucard2);

        //Step 3. Based on Language Setup View, i.e. display correct images in card designs
        switch (language)
        {
            //English is the default design
            case "Khasi":
                setupKhasi();
                break;
            case "Garo":
                setupGaro();
                break;
            case "Hindi":
                setupHindi();
                break;
            case "Pnar":
                setupPnar();
                break;
        }
    }

    private void setupPnar() {
        bcard1.setImageDrawable(getResources().getDrawable(R.drawable.bcard1_pnar));
        bcard2.setImageDrawable(getResources().getDrawable(R.drawable.bcard2_pnar));
        tcard1.setImageDrawable(getResources().getDrawable(R.drawable.thankyou_pnar));
        tcard2.setImageDrawable(getResources().getDrawable(R.drawable.tcard2_pnar));
        gcard1.setImageDrawable(getResources().getDrawable(R.drawable.morning_pnar));
        gcard2.setImageDrawable(getResources().getDrawable(R.drawable.night_pnar));
        gwscard1.setImageDrawable(getResources().getDrawable(R.drawable.getwell_pnar));
        gwscard2.setImageDrawable(getResources().getDrawable(R.drawable.gcard2_pnar));
        atbcard1.setImageDrawable(getResources().getDrawable(R.drawable.allthebest_pnar));
        atbcard2.setImageDrawable(getResources().getDrawable(R.drawable.acard2_pnar));
        pcard1.setImageDrawable(getResources().getDrawable(R.drawable.peace_pnar));
        pcard2.setImageDrawable(getResources().getDrawable(R.drawable.pcard2_pnar));
    }

    private void setupKhasi() {
        bcard1.setImageDrawable(getResources().getDrawable(R.drawable.bcard1_khasi));
        bcard2.setImageDrawable(getResources().getDrawable(R.drawable.bcard2_khasi));
        tcard1.setImageDrawable(getResources().getDrawable(R.drawable.thankyou_khasi));
        tcard2.setImageDrawable(getResources().getDrawable(R.drawable.tcard2_khasi));
        gcard1.setImageDrawable(getResources().getDrawable(R.drawable.morning_khasi));
        gcard2.setImageDrawable(getResources().getDrawable(R.drawable.night_khasi));
        gwscard1.setImageDrawable(getResources().getDrawable(R.drawable.getwell_khasi));
        gwscard2.setImageDrawable(getResources().getDrawable(R.drawable.gcard2_khasi));
        atbcard1.setImageDrawable(getResources().getDrawable(R.drawable.allthebest_khasi));
        atbcard2.setImageDrawable(getResources().getDrawable(R.drawable.acard2_khasi));
        pcard1.setImageDrawable(getResources().getDrawable(R.drawable.peace_khasi));
        pcard2.setImageDrawable(getResources().getDrawable(R.drawable.pcard2_khasi));
    }

    private void setupGaro() {
        bcard1.setImageDrawable(getResources().getDrawable(R.drawable.bcard1_garo));
        bcard2.setImageDrawable(getResources().getDrawable(R.drawable.bcard2_garo));
        tcard1.setImageDrawable(getResources().getDrawable(R.drawable.thankyou_garo));
        tcard2.setImageDrawable(getResources().getDrawable(R.drawable.tcard2_garo));
        gcard1.setImageDrawable(getResources().getDrawable(R.drawable.morning_garo));
        gcard2.setImageDrawable(getResources().getDrawable(R.drawable.night_garo));
        gwscard1.setImageDrawable(getResources().getDrawable(R.drawable.getwell_garo));
        gwscard2.setImageDrawable(getResources().getDrawable(R.drawable.gcard2_garo));
        atbcard1.setImageDrawable(getResources().getDrawable(R.drawable.allthebest_garo));
        atbcard2.setImageDrawable(getResources().getDrawable(R.drawable.acard2_garo));
        pcard1.setImageDrawable(getResources().getDrawable(R.drawable.peace_garo));
        pcard2.setImageDrawable(getResources().getDrawable(R.drawable.pcard2_garo));
    }

    private void setupHindi() {

        bcard1.setImageDrawable(getResources().getDrawable(R.drawable.bcard1_hindi));
        bcard2.setImageDrawable(getResources().getDrawable(R.drawable.bcard2_hindi));
        tcard1.setImageDrawable(getResources().getDrawable(R.drawable.thankyou_hindi));
        tcard2.setImageDrawable(getResources().getDrawable(R.drawable.tcard2_hindi));
        gcard1.setImageDrawable(getResources().getDrawable(R.drawable.morning_hindi));
        gcard2.setImageDrawable(getResources().getDrawable(R.drawable.night_hindi));
        gwscard1.setImageDrawable(getResources().getDrawable(R.drawable.getwell_hindi));
        gwscard2.setImageDrawable(getResources().getDrawable(R.drawable.gcard2_hindi));
        atbcard1.setImageDrawable(getResources().getDrawable(R.drawable.allthebest_hindi));
        atbcard2.setImageDrawable(getResources().getDrawable(R.drawable.acard2_hindi));
        pcard1.setImageDrawable(getResources().getDrawable(R.drawable.peace_hindi));
        pcard2.setImageDrawable(getResources().getDrawable(R.drawable.pcard2_hindi));
    }

    //Step 4. On click of image send the Card Design as another String to the EnterMessageActivity (We will use this design in displaying the Card in GenerateGreetingCard)
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

    public void openSDCard(View view) {} // tried to open SDCARD
}
