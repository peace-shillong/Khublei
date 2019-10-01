package com.sngur.khublei;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EnterMessageActivity extends AppCompatActivity {
    Bundle data;
    String language,design;
    EditText personName;
    Spinner greetSpinner,sizeSpinner,fontSpinner;
    String[] fontSize={"Small","Medium","Large"},fontFace={"Casual","Cursive","Sans","Serif"},greetEng={"Good Morning","Good Night"};
    String[] khasiGreet={"Ka step ba bha","Thiah suk"},garoGreet={"Pringnam","Walnam"},hindiGreet={"शुभ प्रभात","शुभ रात्रि"};

    String[] Birthday={"Sngikha basuk","Garo","Pnar","Hindi","Happy Birthday"};
    String[] ThankYou={"Khublei","Garo","Pnar","Hindi","Thank You"};
    String[] GetWell={"Khlang noh","Garo","Pnar","Hindi","Get Well Soon"};
    String[] AllTheBest={"Sa leh bha","Garo","Pnar","Hindi","All The Best"};
    String[] Peace={"Jingsuk lem bad phi","Garo","Pnar","Hindi","Peace Be With You"};

    String greet="";
    TextView selectGreeting;
    int greetIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_message);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.khublei_hand);

        data=getIntent().getExtras();
        language=data.getString("Language");
        design=data.getString("Card");
        personName=findViewById(R.id.name);

        greetSpinner=findViewById(R.id.greeting_spinner);
        sizeSpinner=findViewById(R.id.font_size_spinner);
        fontSpinner=findViewById(R.id.font_family_spinner);
        fontSpinner.setSelection(2);
        sizeSpinner.setSelection(1);
        selectGreeting=findViewById(R.id.greeting_select);

        selectGreeting.setVisibility(View.GONE);
        if(design.equals("Good Morning")||design.equals("Good Night"))
        {
            selectGreeting.setVisibility(View.VISIBLE);
            greetSpinner.setVisibility(View.VISIBLE);
            if(design.equals("Good Morning"))
                greetSpinner.setSelection(0);
            else
                greetSpinner.setSelection(1);
            ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,greetEng);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            greetSpinner.setAdapter(adapter);
            greetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        //FONT SIZE SPINNER
        ArrayAdapter<String> fadapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,fontSize);
        fadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(fadapter);

        //FONT FACE SPINNER
        ArrayAdapter<String> ffadapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,fontFace);
        ffadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fontSpinner.setAdapter(ffadapter);

        //select Greeting type


    }

    public void generateNewCard(View view) {
        //Toast.makeText(this, "Got it", Toast.LENGTH_SHORT).show();
        //int i=view.getId();
        if(design.equals("Good Morning")||design.equals("Good Night")) {
            greet = greetSpinner.getSelectedItem().toString();
            if (greet.equals("Good Morning"))
                greetIndex = 0;
            else if (greet.equals("Good Night"))
                greetIndex = 1;

            switch(language) {
                case "English":
                    greet = greetEng[greetIndex];
                    break;
                case "Khasi":
                    greet=khasiGreet[greetIndex];
                    break;
                case "Garo":
                    greet=garoGreet[greetIndex];
                    break;
                case "Pnar":
                    greet=khasiGreet[greetIndex];
                    break;
                case "Hindi":
                    greet=hindiGreet[greetIndex];
                    break;

            }
        }
        else
        {
            switch(language) {
                case "English":
                    greetIndex=4;
                    break;
                case "Khasi":
                    greetIndex=0;
                    break;
                case "Garo":
                    greetIndex=1;
                    break;
                case "Pnar":
                    greetIndex=2;
                    break;
                case "Hindi":
                    greetIndex=3;
                    break;
            }
            //Set Greeting based on Card design
            switch (design)
            {
                case "BCard1":
                case "BCard2":
                    greet=Birthday[greetIndex];
                    break;
                case "TCard1":
                case "TCard2":
                    greet=ThankYou[greetIndex];
                    break;
                case "GWSCard1":
                case "GWSCard2":
                    greet=GetWell[greetIndex];
                    break;
                case "ATBCard1":
                case "ATBCard2":
                    greet=AllTheBest[greetIndex];
                    break;
                case "PBWUCard1":
                case "PBWUCard2":
                    greet=Peace[greetIndex];
                    break;
            }
        }


        if(!personName.getText().toString().equals("")) {
            Intent intent = new Intent(this, GenerateGreetingCard.class);
            intent.putExtra("Language", language);
            intent.putExtra("Card",design);
            intent.putExtra("Size",sizeSpinner.getSelectedItem().toString());
            intent.putExtra("Font",fontSpinner.getSelectedItem().toString());
            intent.putExtra("Name", personName.getText().toString());
            if(greet!=null)
            intent.putExtra("Greet",greet);

            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Enter Your Friend's Name",Toast.LENGTH_SHORT).show();
        }

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
