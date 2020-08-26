package com.sngur.khublei;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    EditText personName,greeting;
    Spinner greetSpinner,sizeSpinner,fontSpinner;
    String[] fontSize={"Small","Medium","Large"},fontFace={"Casual","Cursive","Sans","Serif"},greetEng={"Good Morning","Good Night"};
    String[] khasiGreet={"Ka step ba bha","Thiah suk"},garoGreet={"Pringnam","Walnam"},hindiGreet={"शुभ प्रभात","शुभ रात्रि"},pnarGreet={"Ka step wasuk","Thiah suk"};
    String[] Birthday={"Sngikha basuk","Atchinamsal","Sngikha wasuk","जन्मदिन की शुभकामनाएं।","Happy Birthday"};
    String[] ThankYou={"Khublei shibun","Mitela","Khublei chibun","धन्यवाद","Thank You"};
    String[] GetWell={"Khlain noh","Tarake anseng baljokbo","Smat chait","जल्द ठीक हो जाओ","Get Well Soon"};
    String[] AllTheBest={"Sa leh bha","Name dakangbo","Leb bha","शुभकामनाएं","All The Best"};
    String[] Peace={"Jingsuk lem bad phi","Nang baksa tom•tom ong•china","Jingsuk ha phi","आपको शांति मिले","Peace Be With You"};

    String greet="";
    TextView selectGreeting;
    int greetIndex=4;//default is English
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
        greeting=findViewById(R.id.greet);
        greetSpinner=findViewById(R.id.greeting_spinner);
        sizeSpinner=findViewById(R.id.font_size_spinner);
        fontSpinner=findViewById(R.id.font_family_spinner);
        selectGreeting=findViewById(R.id.greeting_select);

        selectGreeting.setVisibility(View.GONE);
        //Displaying Select Greeting Spinner if GOod Morning or Good Night card design is selected
        if(design.equals("Good Morning")||design.equals("Good Night"))
        {
            selectGreeting.setVisibility(View.VISIBLE);
            greetSpinner.setVisibility(View.VISIBLE);
            ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,greetEng);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            greetSpinner.setAdapter(adapter);
            if(design.equals("Good Morning"))
                greetSpinner.setSelection(0);
            else
                greetSpinner.setSelection(1);
            greetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
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

        fontSpinner.setSelection(1);
        sizeSpinner.setSelection(1);

        //Displaying Enter Your Greeting when user chooses English/Others
        switch (language)
        {
            case "English":
                greeting.setVisibility(View.VISIBLE);
                switch (design) {
                    case "Good Morning":
                        greeting.setText("Good Morning");
                        selectGreeting.setVisibility(View.GONE);
                        greetSpinner.setVisibility(View.GONE);
                        greet="Good Morning";
                        break;
                    case "Good Night":
                        greeting.setText("Good Night");
                        selectGreeting.setVisibility(View.GONE);
                        greetSpinner.setVisibility(View.GONE);
                        greet="Good Night";
                        break;
                    case "BCard1":
                    case "BCard2":
                        greet=Birthday[greetIndex];
                        greeting.setText(greet);
                        break;
                    case "TCard1":
                    case "TCard2":
                        greet=ThankYou[greetIndex];
                        greeting.setText(greet);
                        break;
                    case "GWSCard1":
                    case "GWSCard2":
                        greet=GetWell[greetIndex];
                        greeting.setText(greet);
                        break;
                    case "ATBCard1":
                    case "ATBCard2":
                        greet=AllTheBest[greetIndex];
                        greeting.setText(greet);
                        break;
                    case "PBWUCard1":
                    case "PBWUCard2":
                        greet=Peace[greetIndex];
                        greeting.setText(greet);
                        break;
                }
                break;
        }

    }

    public void generateNewCard(View view) {
        if(design.equals("Good Morning")||design.equals("Good Night")) {
            greet = greetSpinner.getSelectedItem().toString();
            if (greet.equals("Good Morning"))
                greetIndex = 0;
            else if (greet.equals("Good Night"))
                greetIndex = 1;
            switch(language) {
                //case "English":
                   // greet = greetEng[greetIndex];
                    //break;
                case "Khasi":
                    greet=khasiGreet[greetIndex];
                    break;
                case "Garo":
                    greet=garoGreet[greetIndex];
                    break;
                case "Pnar":
                    greet=pnarGreet[greetIndex];
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
            if(language.equals("English")) {
                intent.putExtra("Greet",greeting.getText().toString());
            }
            else if(greet!=null)
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
