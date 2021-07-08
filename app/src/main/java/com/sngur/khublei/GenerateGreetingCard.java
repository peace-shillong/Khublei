package com.sngur.khublei;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

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
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class GenerateGreetingCard extends AppCompatActivity {

    private Bundle data;
    private String personName;//language
    private static Button btn2;
    private static LinearLayout myView;
    private TextView greetings;
    private String design, greet,font,size;
    private boolean circlePhoto=false;
    private LinearLayout bgSelect,bcardBg,tcardBg,gcardBg,atbcardBg,pbcardBg;
    private CircleImageView circlePhotoView;
    private ImageView bgImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_greeting_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.khublei_hand);

        btn2= findViewById(R.id.shareMyView);
        myView= findViewById(R.id.myView);
        greetings= findViewById(R.id.greeting);
        bgSelect=findViewById(R.id.bgselect);
        bcardBg=findViewById(R.id.bcardbg);
        tcardBg=findViewById(R.id.tcardbg);
        atbcardBg=findViewById(R.id.atbcardbg);
        gcardBg=findViewById(R.id.gwscardbg);
        pbcardBg=findViewById(R.id.pcardbg);
        circlePhotoView=findViewById(R.id.circlephoto);
        bgImg=findViewById(R.id.photo);

        data=getIntent().getExtras();
        //language=data.getString("Language");
        personName=data.getString("Name");
        design=data.getString("Card");
        greet=data.getString("Greet");
        font=data.getString("Font");
        size=data.getString("Size");

        if(greet!=null)
        greetings.setText( greet+" "+personName);

        //Setting the Font Size
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

        //Setting the Font
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
                greetings.setTypeface(face);
                setTextSize();
                break;
            case "Sans":
                greetings.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);
                break;
            case "Serif":
                greetings.setTypeface(Typeface.SERIF,Typeface.BOLD);
                break;

        }

        //Display Background Images based on Type of Card Design
        switch (design)
        {
            case "Good Night":
                bgSelect.setVisibility(View.VISIBLE);
                pbcardBg.setVisibility(View.VISIBLE);
                tcardBg.setVisibility(View.VISIBLE);
                circlePhotoView.setVisibility(View.VISIBLE);
                bgImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                circlePhoto=true;
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgf));
                break;
            case "BCard2":
                bgSelect.setVisibility(View.VISIBLE);
                bcardBg.setVisibility(View.VISIBLE);
                circlePhotoView.setVisibility(View.VISIBLE);
                bgImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                circlePhoto=true;
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg1));
                break;
            case "TCard2":
                bgSelect.setVisibility(View.VISIBLE);
                tcardBg.setVisibility(View.VISIBLE);
                circlePhotoView.setVisibility(View.VISIBLE);
                bgImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                circlePhoto=true;
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg5));
                break;
            case "ATBCard2":
                bgSelect.setVisibility(View.VISIBLE);
                atbcardBg.setVisibility(View.VISIBLE);
                circlePhotoView.setVisibility(View.VISIBLE);
                bgImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                circlePhoto=true;
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg8));
                break;
            case "GWSCard2":
                bgSelect.setVisibility(View.VISIBLE);
                gcardBg.setVisibility(View.VISIBLE);
                circlePhotoView.setVisibility(View.VISIBLE);
                bgImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                circlePhoto=true;
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg9));
                break;
            case "PBWUCard2":
                bgSelect.setVisibility(View.VISIBLE);
                pbcardBg.setVisibility(View.VISIBLE);
                circlePhotoView.setVisibility(View.VISIBLE);
                bgImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                circlePhoto=true;
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgf));
                break;
            default:
                bgImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON).setActivityTitle("Crop Image").start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                //Enable btn2 if image is selected
                btn2.setEnabled(true);

                if(circlePhoto)
                    ((CircleImageView) findViewById(R.id.circlephoto)).setImageURI(result.getUri());
                else
                    ((ImageView) findViewById(R.id.photo)).setImageURI(result.getUri());
                Toast.makeText(
                        this, "Cropping successful", Toast.LENGTH_LONG)
                        .show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }

        //btn2 set onCLickListener
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
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Khublei_image_"+ Calendar.getInstance().getTime()+".jpg");
                    FileOutputStream stream = new FileOutputStream(file);
                    b.compress(Bitmap.CompressFormat.JPEG, 100, stream);
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
                    myView.destroyDrawingCache();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(GenerateGreetingCard.this, "Image is too large, Please Crop the image with a smaller size", Toast.LENGTH_SHORT).show();
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
    //on Click of Image set the Background Image Accordingly
    public void setBg(View view) {
        int i=view.getId();
        switch (i)
        {
            case R.id.bg1:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg1));
                break;
            case R.id.bg2:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg2));
                break;
            case R.id.bg3:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg3));
                break;
            case R.id.bg4:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg4));
                break;
            case R.id.bg5:
            case R.id.bg5a:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg5));
                break;
            case R.id.bg6:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg6));
                break;
            case R.id.bg7:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg7));
                break;
            case R.id.bg8:
            case R.id.bg8a:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg8));
                break;
            case R.id.bg9:
            case R.id.bg9a:
            case R.id.bg9b:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bg9));
                break;
            case R.id.bga:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bga));
                break;
            case R.id.bgb:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgb));
                break;
            case R.id.bgc:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgc));
                break;
            case R.id.bgd:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgd));
                break;
            case R.id.bge:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bge));
                break;
            case R.id.bgf:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgf));
                break;
            case R.id.bgg:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgg));
                break;
            case R.id.bgh1:
            case R.id.bgh2:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgh));
                break;
            case R.id.bgi:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgi));
                break;
            case R.id.bgj:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgj));
                break;
            case R.id.bgk:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgk));
                break;
            case R.id.bgl:
                bgImg.setImageDrawable(getResources().getDrawable(R.drawable.bgl));
                break;
        }
    }
}
