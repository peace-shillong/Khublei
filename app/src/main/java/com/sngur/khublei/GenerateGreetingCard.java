package com.sngur.khublei;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;

public class GenerateGreetingCard extends AppCompatActivity {

    Bundle data;
    String language,personName;

    private static int RESULT_LOAD_IMAGE = 1;
    static Button btn2;
    static LinearLayout myView;
    TextView greetings;
    private String design, greet,font,size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_greeting_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.khublei_hand);

        btn2= findViewById(R.id.shareMyView);
        myView= findViewById(R.id.myView);
        greetings= findViewById(R.id.greeting);

        data=getIntent().getExtras();
        language=data.getString("Language");
        personName=data.getString("Name");
        design=data.getString("Card");
        greet=data.getString("Greet");
        font=data.getString("Font");
        size=data.getString("Size");

        if(greet!=null)
        greetings.setText( greet+" "+personName);

        switch (size)
        {
            case "Small":
                greetings.setTextSize(18);
                break;
            case "Large":
                greetings.setTextSize(24);
                break;
            default:
                greetings.setTextSize(20);
        }
        Typeface face;

        switch (font)
        {
            case "Casual":
                face = Typeface.createFromAsset(getAssets(),"fonts/casual.ttf");
                greetings.setTypeface(face);
                setTextSize();
                break;
            case "Cursive":
                face = Typeface.createFromAsset(getAssets(),"fonts/cursive.ttf");

//                greetings.setTypeface(Typeface.DEFAULT);
                break;
            case "Sans":
                greetings.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
                break;
            case "Serif":
                greetings.setTypeface(Typeface.SERIF,Typeface.BOLD);
                break;

        }

        //SELECT Background
        switch (design)
        {
            case "BCard2":

                break;
            case "TCard2":
                break;
            case "ATBCard2":
                break;
            case "GWSCard2":
                break;
            case "PBWUCard2":
                break;
        }
    }

    private void setTextSize() {
        if(greetings.getTextSize()>22)
            greetings.setTextSize(26);
        else if(greetings.getTextSize()>19)
            greetings.setTextSize(24);
        else
            greetings.setTextSize(20);
    }

    public void openGallery(View view) {
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).setActivityTitle("Crop Image").setRequestedSize(300, 600).start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                ((ImageView) findViewById(R.id.photo)).setImageURI(result.getUri());
                Toast.makeText(
                        this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG)
                        .show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }

        //Enable btn2 and set onCLickListener
        btn2.setEnabled(true);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                //save and share image
                Toast.makeText(GenerateGreetingCard.this, "Preparing to share Greeting Card", Toast.LENGTH_SHORT).show();
                myView.setDrawingCacheEnabled(true);
                Bitmap b = myView.getDrawingCache();
                try {
                    //Create File and compress it
                    File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "shared_image.png");
                    FileOutputStream stream = new FileOutputStream(file);
                    b.compress(Bitmap.CompressFormat.JPEG, 95, stream);
                    stream.flush();
                    stream.close();
                    //if SDK below else
                    //Uri uri = Uri.fromFile(file);
                    Uri uri  = FileProvider.getUriForFile(GenerateGreetingCard.this, getApplicationContext().getPackageName()+ ".provider", file);
                    //share
                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_STREAM, uri);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("image/*");
                    intent.putExtra(Intent.EXTRA_TEXT, greet+" "+personName+"!");
                    startActivity(Intent.createChooser(intent, "Share"));


                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
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
