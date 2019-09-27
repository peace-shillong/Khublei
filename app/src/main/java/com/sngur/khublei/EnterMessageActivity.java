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
        selectGreeting=findViewById(R.id.greeting_select);

        selectGreeting.setVisibility(View.GONE);
        if(design.equals("Good Morning")||design.equals("Good Night"))
        {
            selectGreeting.setVisibility(View.VISIBLE);
            greetSpinner.setVisibility(View.VISIBLE);

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
        /*switch (i)
        {
           case R.id.createView:

               break;
        }
        */

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
